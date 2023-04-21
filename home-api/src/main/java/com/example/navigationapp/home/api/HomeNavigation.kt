package com.example.navigationapp.home.api

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface HomeNavigation {
    val startDestination: String
    fun NavGraphBuilder.registerGraph(navController: NavController)
}