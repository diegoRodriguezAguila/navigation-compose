package com.example.navigationapp.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.navigationapp.home.api.HomeNavigation
import com.example.navigationapp.home.ui.HomeScreen
import com.example.settings.api.SettingsNavGraphProvider
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject


internal class HomeNavigationImpl @Inject constructor(
    private val settingsNavGraphProvider: SettingsNavGraphProvider
    ) : HomeNavigation {
    override fun createHome(modifier: Modifier): @Composable () -> Unit {
        return {
            HomeScreen(
                modifier = modifier,
                settings = settingsNavGraphProvider,
            )
        }
    }

//    @Composable
//    private fun rememberSettingsNavGraphProvider(): SettingsNavGraphProvider {
//        val context = LocalContext.current
//        return remember {
//            EntryPoints.get(context, HomeEntryPoint::class.java).settingsNavGraphProvider()
//        }
//    }
}
//
//@EntryPoint
//@InstallIn(ActivityComponent::class)
//interface HomeEntryPoint {
//    fun settingsNavGraphProvider(): SettingsNavGraphProvider
//}
