package com.example.navigationapp.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.navigationapp.home.R
import com.example.settings.api.SettingsNavGraphProvider

@Composable
fun HomeScreen(
    settings: SettingsNavGraphProvider,
    modifier: Modifier = Modifier,
    topLevelDestinations: List<TopLevelDestination> = getTopLevelDestinations(settings),
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: topLevelDestinations.first().route
    val navigationActions = remember(navController) {
        NavigationActions(navController)
    }

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        HomeNavHost(
            navController = navController,
            topLevelDestinations = topLevelDestinations,
            modifier = Modifier.weight(1f),
        )
        HomeBottomNavigationBar(
            selectedDestination = selectedDestination,
            navigateToTopLevelDestination = navigationActions::navigateTo,
            topLevelDestinations = topLevelDestinations,
        )
    }
}

@Composable
private fun HomeNavHost(
    navController: NavHostController,
    topLevelDestinations: List<TopLevelDestination>,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = topLevelDestinations.first().route,
    ) {
        topLevelDestinations.forEach { destination ->
            with(destination) {
                registerGraph(navController)
            }
        }
    }
}

@Composable
private fun HomeBottomNavigationBar(
    topLevelDestinations: List<TopLevelDestination>,
    selectedDestination: String,
    navigateToTopLevelDestination: (TopLevelDestination) -> Unit
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        topLevelDestinations.forEach { destination ->
            NavigationBarItem(
                selected = selectedDestination.contains(destination.route),
                onClick = { navigateToTopLevelDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = stringResource(id = destination.iconTextId)
                    )
                }
            )
        }
    }
}

private fun getTopLevelDestinations(settings: SettingsNavGraphProvider) = listOf(
    TopLevelDestination(
        route = "list",
        selectedIcon = Icons.Default.List,
        unselectedIcon = Icons.Default.List,
        iconTextId = R.string.tab_list,
        registerGraph = { navController ->
            composable(route = "list") {
                EmptyComingSoon(
                    title = "List Feature",
                    subtitle = "This list is under construction!"
                )
            }
        }
    ),
    TopLevelDestination(
        route = "settings",
        selectedIcon = Icons.Default.Settings,
        unselectedIcon = Icons.Default.Settings,
        iconTextId = R.string.tab_settings,
        registerGraph = {
            with(settings) {
                createSettingsGraph(it)
            }
        },
    ),
)