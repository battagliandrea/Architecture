package com.battman.sample.feature.one.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.battman.domain.models.SampleModel
import com.battman.sample.common.theme.SampleTheme
import com.battman.sample.common.theme.SampleTheme.colors
import com.battman.sample.common.theme.SampleTheme.dimensions
import com.battman.sample.common.theme.SampleTheme.typography
import com.battman.sample.feature.one.presentation.FeatureOneContract.UiEvent.NavigateToFeatureTwo
import com.battman.sample.feature.one.presentation.FeatureOneContract.UiState
import com.battman.sample.feature.one.presentation.FeatureOneContract.UiState.Failure
import com.battman.sample.feature.one.presentation.FeatureOneContract.UiState.Loading
import com.battman.sample.feature.one.presentation.FeatureOneContract.UiState.Success

@Composable
internal fun FeatureOneScreen(
    viewModel: FeatureOneViewModel,
    navigateToFeatureTwo: () -> Unit,
) {
    val (state, events, processIntent) = viewModel

    LaunchedEffect(key1 = Unit) {
        events.collect { event ->
            when (event) {
                is NavigateToFeatureTwo -> {
                    navigateToFeatureTwo()
                }
            }
        }
    }

    FeatureOneScreen(
        state = state,
    )
}

@Composable
internal fun FeatureOneScreen(
    state: UiState,
    modifier: Modifier = Modifier,
    onClick: (id: String) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        containerColor = colors.background,
    ) { paddingValues ->
        when (state) {
            Loading -> {
                Text(
                    text = "LOADING",
                    color = colors.primary,
                    style = typography.bodyLarge,
                )
            }
            is Success -> {
                SampleModelLazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    models = state.models,
                    onClick = onClick
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

@Composable
internal fun SampleModelLazyColumn(
    models: List<SampleModel>,
    modifier: Modifier = Modifier,
    onClick: (id: String) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(dimensions.spacingXS),
    ) {
        items(models.size) { index ->
            SampleModelItem(
                model = models[index],
                onClick = onClick,
            )
        }
    }
}

@Composable
internal fun SampleModelItem(
    model: SampleModel,
    modifier: Modifier = Modifier,
    onClick: (id: String) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensions.spacingXS)
            .clickable { onClick(model.id) },
    ) {
        Text(
            text = model.name,
            color = colors.primary,
            style = typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(dimensions.spacingXS))
        Text(
            text = model.description,
            color = colors.onSurface,
            style = typography.labelSmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FeatureOneScreenPreview() {
    val models = listOf(
        SampleModel(id = "1", name = "Sample 1", description = "Description 1"),
        SampleModel(id = "2", name = "Sample 2", description = "Description 2"),
        SampleModel(id = "3", name = "Sample 3", description = "Description 3"),
        SampleModel(id = "4", name = "Sample 4", description = "Description 4"),
        SampleModel(id = "5", name = "Sample 5", description = "Description 5"),
    )

    SampleTheme {
        FeatureOneScreen(
            state = Success(models),
        )
    }
}
