package com.battman.sample.feature.two.presentation

import com.battman.core.ui.mvi.MVIEvent
import com.battman.core.ui.mvi.MVIIntent
import com.battman.core.ui.mvi.MVIState
import com.battman.domain.models.SampleModel

internal sealed interface FeatureTwoContract {

    sealed class UiIntent : MVIIntent

    sealed class UiState : MVIState {
        data object Loading : UiState()
        data class Success(
            val model: SampleModel,
        ) : UiState()
        data class Failure(
            val message: String,
        ) : UiState()
    }

    sealed class UiEvent : MVIEvent
}
