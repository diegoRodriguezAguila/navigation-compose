package com.example.navigationapp.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationapp.home.api.HomeNavigation
import com.example.navigationapp.home.ui.HomeScreen
import javax.inject.Inject

internal class HomeNavigationImpl @Inject constructor() : HomeNavigation {
    override val startDestination: String = "home"

    override fun NavGraphBuilder.registerGraph(navController: NavController) {
        composable("home") {
            HomeScreen()
        }
    }
}