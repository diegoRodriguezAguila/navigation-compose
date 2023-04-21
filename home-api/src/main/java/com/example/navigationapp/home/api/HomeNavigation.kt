package com.example.navigationapp.home.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface HomeNavigation {
    fun createHome(modifier: Modifier): (@Composable () -> Unit)
}