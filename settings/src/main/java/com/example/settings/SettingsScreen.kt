package com.example.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavGraphBuilder.registerSettingsGraph(navController: NavHostController) {
    navigation(startDestination = "settings", route="app_settings") {
        composable("settings") {
            SettingsScreen(onGoToDetails = { details ->
                navController.navigate("settings/$details")
            })
        }
        composable("settings/{details}") { backStackEntry ->
            val details = backStackEntry.arguments?.getString("details") ?: "NO DETAILS"
            SettingsDetail(title = details) {
                navController.popBackStack()
            }
        }
    }
}

@Composable
private fun SettingsScreen(
    modifier: Modifier = Modifier,
    onGoToDetails: (String)-> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        SETTINGS.forEach { setting ->
            Button(onClick = {
                onGoToDetails(setting)
            }) {
                Text(text = setting, fontStyle = MaterialTheme.typography.bodyLarge.fontStyle)
            }
        }
    }
}

val SETTINGS = listOf("Account", "Notifications", "About")