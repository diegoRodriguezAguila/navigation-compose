package com.example.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
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