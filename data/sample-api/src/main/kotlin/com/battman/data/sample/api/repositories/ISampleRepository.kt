package com.battman.data.sample.api.repositories

import arrow.core.Either
import com.battman.domain.models.SampleModel

interface ISampleRepository {

    suspend fun getModels(): Either<Unit, List<SampleModel>>
}