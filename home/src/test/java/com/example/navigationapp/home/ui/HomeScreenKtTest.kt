package com.example.navigationapp.home.ui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowLog

@RunWith(RobolectricTestRunner::class)
internal class HomeScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        ShadowLog.stream = System.out
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            HomeScreen(navController = navController)
        }
    }

    // Unit test
    @Test
    fun `verify home screen start destination is the list feature`() {
        assertEquals(navController.currentDestination?.route, "list")

        composeTestRule
            .onNodeWithText("List Feature")
            .assertIsDisplayed()

    }

    @Test
    fun `verify home screen navigates to settings on click`(): Unit = with(composeTestRule) {
        onNodeWithContentDescription("Settings")
            .performClick()

        assertEquals(navController.currentDestination?.route, "settings")

        onNodeWithText("Settings Feature")
            .assertIsDisplayed()

    }
}