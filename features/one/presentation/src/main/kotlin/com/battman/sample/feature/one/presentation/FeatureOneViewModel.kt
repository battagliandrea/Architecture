package com.battman.sample.feature.one.presentation

import androidx.lifecycle.viewModelScope
import com.battman.core.ui.mvi.MVIModel
import com.battman.domain.usecases.GetModelsUseCase
import com.battman.sample.feature.one.presentation.FeatureOneContract.UiEvent
import com.battman.sample.feature.one.presentation.FeatureOneContract.UiIntent
import com.battman.sample.feature.one.presentation.FeatureOneContract.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FeatureOneViewModel @Inject constructor(
    private val getModelsUseCase: GetModelsUseCase,
) : MVIModel<UiState, UiIntent, UiEvent>() {

    override fun createInitialState() = UiState.Loading

    override fun handleIntent(intent: UiIntent) {}

    init {
        fetchModels()
    }

    private fun fetchModels() {
        viewModelScope.launch {
            getModelsUseCase.execute(params = GetModelsUseCase.Params)
                .fold(
                    ifRight = { setState { UiState.Success(models = it) } },
                    ifLeft = {
                        setState {
                            UiState.Failure(
                                message = "There was an error while fetching the models!",
                            )
                        }
                    },
                )
        }
    }
}
