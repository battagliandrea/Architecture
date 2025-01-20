package com.battman.domain.usecases

import arrow.core.Either
import com.battman.domain.usecases.GetModelsUseCase.Params
import com.battman.core.usecase.api.IUseCase
import com.battman.data.sample.api.repositories.ISampleRepository
import com.battman.domain.models.SampleModel
import javax.inject.Inject

class GetModelsUseCase @Inject constructor(
    private val sampleRepository: ISampleRepository,
) : IUseCase<Params, Either<Unit, List<SampleModel>>>() {

    object Params

    override suspend fun execute(params: Params) =
        sampleRepository.getModels()
}
