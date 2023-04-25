package com.example.settings.api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface SettingsNavGraphProvider {
    fun  NavGraphBuilder.createSettingsGraph(navController: NavHostController)
}