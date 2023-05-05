package com.example.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.example.settings.api.SettingsNavGraphProvider
import javax.inject.Inject

internal class SettingsNavGraphProviderImpl @Inject constructor() : SettingsNavGraphProvider {
    override fun NavGraphBuilder.createSettingsGraph(navController: NavHostController) {
        navigation(startDestination = "settings", route = "app_settings") {
            composable(
                route = "settings",
                deepLinks = listOf(
                    navDeepLink { uriPattern = "$baseUrl/settings" },
                )
            ) {
                SettingsScreen(onGoToDetails = { details ->
                    navController.navigate("settings/$details")
                })
            }
            composable(
                route = "settings/{details}",
                deepLinks = listOf(
                    navDeepLink { uriPattern = "$baseUrl/settings/{details}" },
                ),
            ) { backStackEntry ->
                val details = backStackEntry.arguments?.getString("details") ?: "NO DETAILS"
                SettingsDetail(title = details) {
                    navController.popBackStack()
                }
            }
        }
    }
}

val baseUrl = "compose-navigation://example.com"