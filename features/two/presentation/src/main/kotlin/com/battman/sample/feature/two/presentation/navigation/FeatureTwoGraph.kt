package com.battman.sample.feature.two.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.battman.sample.feature.two.presentation.FeatureTwoScreen
import com.battman.sample.feature.two.presentation.FeatureTwoViewModel

const val FEATURE_TWO_PARAM_MODEL_ID = "modelId"

const val FEATURE_TWO_ROUTE = "feature-two/{$FEATURE_TWO_PARAM_MODEL_ID}"

fun NavController.navigateToFeatureTwo(modelId: String) {
    val route = FEATURE_TWO_ROUTE.replace(
        oldValue = "{$FEATURE_TWO_PARAM_MODEL_ID}",
        newValue = modelId
    )
    navigate(route)
}

fun NavGraphBuilder.featureTwoScreen() {
    composable(
        route = FEATURE_TWO_ROUTE,
        arguments = listOf(navArgument(FEATURE_TWO_PARAM_MODEL_ID) { type = NavType.StringType })
    ) { backStackEntry ->
        val modelId = backStackEntry.arguments?.getString(FEATURE_TWO_PARAM_MODEL_ID).orEmpty()
        FeatureTwoScreen(
            viewModel = hiltViewModel<FeatureTwoViewModel, FeatureTwoViewModel.Factory>(
                creationCallback = { it.create(modelId = modelId) }
            ),
        )
    }
}
