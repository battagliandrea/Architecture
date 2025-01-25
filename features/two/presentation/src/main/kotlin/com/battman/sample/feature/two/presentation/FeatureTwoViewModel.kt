package com.battman.sample.feature.two.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.battman.core.ui.mvi.MVIModel
import com.battman.domain.usecases.GetModelByIdUseCase
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiEvent
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiIntent
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiState
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiState.Failure
import com.battman.sample.feature.two.presentation.FeatureTwoContract.UiState.Success
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = FeatureTwoViewModel.Factory::class)
internal class FeatureTwoViewModel @AssistedInject constructor(
    @Assisted private val modelId: String,
    private val getModelByIdUseCase: GetModelByIdUseCase,
) : MVIModel<UiState, UiIntent, UiEvent>() {

    @AssistedFactory
    interface Factory {
        fun create(modelId: String): FeatureTwoViewModel
    }

    override fun createInitialState() = UiState.Loading

    override fun handleIntent(intent: UiIntent) {}

    init {
        fetchModel()
    }

    private fun fetchModel() {
        viewModelScope.launch {
            getModelByIdUseCase.execute(params = GetModelByIdUseCase.Params(modelId = modelId))
                .fold(
                    ifRight = {
                        setState { Success(model = it) }
                    },
                    ifLeft = {
                        setState {
                            Failure(
                                message = "There was an error while fetching the model!",
                            )
                        }
                    },
                )
        }
    }
}
