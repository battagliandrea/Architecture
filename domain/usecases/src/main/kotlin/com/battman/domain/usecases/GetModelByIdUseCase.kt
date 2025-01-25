package com.battman.domain.usecases

import arrow.core.Either
import com.battman.core.usecase.api.IUseCase
import com.battman.data.sample.api.repositories.ISampleRepository
import com.battman.domain.models.SampleModel
import com.battman.domain.usecases.GetModelByIdUseCase.Params
import javax.inject.Inject

class GetModelByIdUseCase @Inject constructor(
    private val sampleRepository: ISampleRepository,
) : IUseCase<Params, Either<Unit, SampleModel>>() {

    data class Params(
        val modelId: String,
    )

    override suspend fun execute(params: Params) =
        sampleRepository.getModelById(params.modelId)

}
