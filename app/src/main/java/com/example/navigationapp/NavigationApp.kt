package com.example.navigationapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.navigationapp.home.api.HomeNavigation

@Composable
fun NavigationApp(homeNavigation: HomeNavigation) {
    homeNavigation.createHome(modifier = Modifier.fillMaxSize())()
}