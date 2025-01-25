package com.battman.sample.feature.two.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.battman.domain.models.SampleModel
import com.battman.sample.common.theme.SampleTheme
import com.battman.sample.common.theme.SampleTheme.colors
import com.battman.sample.common.theme.SampleTheme.typography
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiState
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiState.Failure
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiState.Loading
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiState.Success

@Composable
internal fun FeatureTwoScreen(
    viewModel: FeatureTwoViewModel,
) {
    val (state, events, processIntent) = viewModel

    LaunchedEffect(key1 = Unit) {
        events.collect { event -> }
    }

    FeatureTwoScreen(
        state = state,
    )
}

@Composable
internal fun FeatureTwoScreen(
    state: UiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        containerColor = colors.background,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues),
        ) {
            when (state) {
                Loading -> {
                    Text(
                        text = "LOADING",
                        color = colors.primary,
                        style = typography.bodyLarge,
                    )
                }
                is Success -> {
                    Text(
                        text = state.model.name,
                        color = colors.primary,
                        style = typography.bodyLarge,
                    )
                }
                is Failure -> {
                    Text(
                        text = state.message,
                        color = colors.primary,
                        style = typography.bodyLarge,
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun FeatureTwoScreenPreview() {
    val model = SampleModel(id = "1", name = "Sample 1", description = "Description 1")

    SampleTheme {
        FeatureTwoScreen(
            state = Success(model),
        )
    }
}
