package com.example.navigationapp.home.api

import androidx.navigation.NamedNavArgument
import com.example.navigationapp.nav.NavRoute

enum class HomeRoutes(
    override val basePath: String,
    override val arguments: List<NamedNavArgument> = emptyList(),
) : NavRoute {
    Home(basePath = "home")
}