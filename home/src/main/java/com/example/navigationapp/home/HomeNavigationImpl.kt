package com.example.navigationapp.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigationapp.home.api.HomeNavigation
import com.example.navigationapp.home.ui.HomeScreen
import javax.inject.Inject

internal class HomeNavigationImpl @Inject constructor() : HomeNavigation {
    override fun createHome(modifier: Modifier): @Composable () -> Unit {
        return {
            HomeScreen(modifier = modifier)
        }
    }
}