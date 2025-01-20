package com.battman.cleanarchitecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.battman.sample.feature.one.presentation.navigation.FEATURE_ONE_ROUTE
import com.battman.sample.feature.one.presentation.navigation.featureOneScreen

@Composable
fun RootHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = FEATURE_ONE_ROUTE,
    ) {
        featureOneScreen(
            onNavigateToFeatureTwo = {},
        )
    }
}
