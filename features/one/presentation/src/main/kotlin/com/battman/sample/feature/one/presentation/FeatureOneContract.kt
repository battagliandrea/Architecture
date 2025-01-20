package com.battman.sample.feature.one.presentation

import com.battman.core.ui.mvi.MVIEvent
import com.battman.core.ui.mvi.MVIIntent
import com.battman.core.ui.mvi.MVIState
import com.battman.domain.models.SampleModel

internal sealed interface FeatureOneContract {

    sealed class UiIntent : MVIIntent

    sealed class UiState : MVIState {
        data object Loading : UiState()
        data class Success(
            val models: List<SampleModel>,
        ) : UiState()
        data class Failure(
            val message: String,
        ) : UiState()
    }

    sealed class UiEvent : MVIEvent {
        data object NavigateToFeatureTwo : UiEvent()
    }
}
