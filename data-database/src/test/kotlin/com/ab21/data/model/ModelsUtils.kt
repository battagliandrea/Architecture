package com.ab21.data.model

import kotlinx.serialization.Serializable
import java.util.UUID
import kotlin.random.Random

@Serializable
data class TestModelWithGeneric<T>(
    val field1: Long,
    val field2: Boolean,
    val field3: String,
    val field4: T
)

@Serializable
data class TestModel(
    val field1: Long
)

object TestModels {

    fun mockModels() =
        (1..100).map{ index ->
            TestModelWithGeneric(
                field1 = Random.nextLong(),
                field2 = Random.nextBoolean(),
                field3 = UUID.randomUUID().toString(),
                field4 = TestModel(
                    field1 = Random.nextLong()
                )
            )
        }
}