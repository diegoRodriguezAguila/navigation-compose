package com.example.navigationapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.navigationapp.home.api.HomeNavigation

@Composable
fun NavigationApp(homeNavigation: HomeNavigation) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = homeNavigation.startDestination) {
        with(homeNavigation) {
            registerGraph(navController)
        }
    }
}