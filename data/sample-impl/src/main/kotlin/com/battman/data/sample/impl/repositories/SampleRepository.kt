package com.battman.data.sample.impl.repositories

import arrow.core.Either
import com.battman.data.sample.api.repositories.ISampleRepository
import com.battman.domain.models.SampleModel
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRepository @Inject constructor(
) : ISampleRepository {

    private val modelsDataSource = listOf(
        SampleModel(id = UUID.randomUUID().toString(), name = "Sample 1", description = "Description 1"),
        SampleModel(id = UUID.randomUUID().toString(), name = "Sample 2", description = "Description 2"),
        SampleModel(id = UUID.randomUUID().toString(), name = "Sample 3", description = "Description 3"),
        SampleModel(id = UUID.randomUUID().toString(), name = "Sample 4", description = "Description 4"),
        SampleModel(id = UUID.randomUUID().toString(), name = "Sample 5", description = "Description 5"),
    )

    override suspend fun getModels(): Either<Unit, List<SampleModel>> =
        Either.Right(modelsDataSource)

    override suspend fun getModelById(id: String): Either<Unit, SampleModel> =
        modelsDataSource
            .find { it.id == id }
            ?.let { Either.Right(it) }
            ?: Either.Left(Unit)

}
