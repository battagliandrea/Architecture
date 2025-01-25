package com.battman.sample.feature.one.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.battman.sample.feature.one.presentation.FeatureOneScreen

const val FEATURE_ONE_ROUTE = "feature-one"

fun NavController.navigateToFeatureOne() {
    navigate(FEATURE_ONE_ROUTE)
}

fun NavGraphBuilder.featureOneScreen(
    onNavigateToFeatureTwo: (modelId: String) -> Unit,
) {
    composable(route = FEATURE_ONE_ROUTE) {
        FeatureOneScreen(
            viewModel = hiltViewModel(),
            navigateToFeatureTwo = { onNavigateToFeatureTwo(it) },
        )
    }
}
