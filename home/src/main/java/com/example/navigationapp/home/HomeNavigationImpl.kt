package com.example.navigationapp.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.navigationapp.home.api.HomeNavigation
import com.example.navigationapp.home.api.HomeRoutes
import com.example.navigationapp.home.ui.HomeScreen
import javax.inject.Inject

internal class HomeNavigationImpl @Inject constructor() : HomeNavigation {
    override val startDestination: String = HomeRoutes.Home.route()

    override fun NavGraphBuilder.registerGraph(navController: NavController) {
        composable(startDestination) {
            HomeScreen()
        }
    }
}