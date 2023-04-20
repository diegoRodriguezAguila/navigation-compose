package com.example.navigationapp.nav

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

interface NavRoute {
    val basePath: String
    val arguments: List<NamedNavArgument>

    private val mandatoryArgs: List<NamedNavArgument>
        get() = arguments.filter { !it.argument.isOptional }

    private val optionalArgs: List<NamedNavArgument>
        get() = arguments.filter { it.argument.isOptional }

    fun buildUrl(vararg args: Any?): String {
        val mandatory = mandatoryArgs
        val optional = optionalArgs

        if (args.size < mandatory.size) {
            throw IllegalArgumentException(
                "Not enough arguments to build url, needs at least ${mandatory.size} arguments",
            )
        }
        if (args.size > mandatory.size + optional.size) {
            throw IllegalArgumentException(
                "Too many arguments to build url, needs ${mandatory.size} mandatory arguments " +
                    "and a maximum of ${optional.size} optional arguments",
            )
        }

        validateArguments(args)

        val mandatoryPrefix = if (mandatory.isEmpty()) "" else "/"
        val mandatoryValues = args.take(mandatory.size)
            .joinToString(separator = "/", prefix = mandatoryPrefix) { it?.let { encodeParameter(it) } ?: "null" }

        val optArgs = args.takeLast(args.size - mandatory.size)
        val namedOptionalValues = optArgs.mapIndexedNotNull { i, arg ->
            arg?.let { value ->
                optional[i].name to value
            }
        }

        val optionalPrefix = if (namedOptionalValues.isEmpty()) "" else "?"
        val optionalValues = namedOptionalValues.joinToString(separator = "&", prefix = optionalPrefix) {
            "${it.first}=${encodeParameter(it.second)}"
        }

        return "$basePath$mandatoryValues$optionalValues"
    }

    private fun validateArguments(args: Array<out Any?>) {
        args.forEachIndexed { i, arg ->
            val navArgument = arguments[i]
            val actualType = NavType.inferFromValueType(arg)
            if (navArgument.argument.type != actualType) {
                throw IllegalArgumentException(
                    "Argument ${navArgument.name} at position $i type doesn't match declared" +
                        "argument type ${navArgument.argument.type}, was ${actualType.javaClass.name} instead",
                )
            }
        }
    }

    fun buildDeeplink(vararg args: Any): String = "$APP_SCHEMA${buildUrl(*args)}"

    fun route(): String {
        val mandatory = mandatoryArgs
        val mandatoryPrefix = if (mandatory.isEmpty()) "" else "/"
        val mandatoryParams = mandatory.joinToString(separator = "/", prefix = mandatoryPrefix) { "{${it.name}}" }

        val optional = optionalArgs
        val optionalPrefix = if (optional.isEmpty()) "" else "?"
        val optParams = optional.joinToString(separator = "&", prefix = optionalPrefix) { "${it.name}={${it.name}}" }

        return "$basePath$mandatoryParams$optParams"
    }

    fun deeplinkRoute(): String = "$APP_SCHEMA${route()}"

    private fun encodeParameter(param: Any) = URLEncoder.encode(param.toString(), StandardCharsets.UTF_8.name())
        .replace("+", "%20")

    /**
     * Follows the standard compose navigation
     * [optional nav arguments](https://developer.android.com/jetpack/compose/navigation#optional-args) rules
     */
    private val NavArgument.isOptional: Boolean
        get() = isNullable || isDefaultValuePresent

    private companion object {
        private const val APP_SCHEMA = "glovoapp://"
    }
}
