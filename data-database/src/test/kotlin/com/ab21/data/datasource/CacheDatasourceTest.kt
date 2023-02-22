package com.ab21.data.datasource

import com.ab21.core.errors.Error.DatabaseError
import com.ab21.data.database.dao.CacheDatabase
import com.ab21.data.database.datasource.CacheDatasource
import com.ab21.data.model.ResourcesUtils
import com.ab21.data.model.TestModel
import com.ab21.data.model.TestModelWithGeneric
import com.ab21.data.model.TestModels
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
internal class CacheDatasourceTest {

    private lateinit var cacheDatasource: ICacheDatasource

    private val serializer = ListSerializer(TestModelWithGeneric.serializer(TestModel.serializer()))

    @MockK
    lateinit var database: CacheDatabase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        cacheDatasource = CacheDatasource(database)
    }

    @Test
    fun `given a cached entry then response should be right`() = runTest {
        val cacheItem = ResourcesUtils.getCacheItemFromResources("test_models.json")
        val key = cacheItem.key

        coEvery { database.cacheItemDao().findByKey(key) } returns cacheItem

        cacheDatasource.get(key= key, serializer= serializer).shouldBeRight()
    }

    @Test
    fun `given a malformed cached entry then response should be left`() = runTest {
        val cacheItem = ResourcesUtils.getCacheItemFromResources("test_models_malformed.json")
        val key = cacheItem.key

        coEvery { database.cacheItemDao().findByKey(key) } returns cacheItem

        cacheDatasource.get(key= key, serializer= serializer).shouldBeLeft(DatabaseError.ReadingError)
    }

    @Test
    fun `given data to cache then response should be right`() = runTest {
        val data = TestModels.mockModels()

        coEvery { database.cacheItemDao().insert(any()) } returns 1

        cacheDatasource.set(key = "key", data = data, serializer = serializer).shouldBeRight()
    }

    @Test
    fun `given data to cache when an exception is catched then response should be left`() = runTest {
        val data = TestModels.mockModels()

        coEvery { database.cacheItemDao().insert(any()) } throws RuntimeException("Writing Error")

        cacheDatasource.set(key = "key", data = data, serializer = serializer).shouldBeLeft(DatabaseError.WritingError)
    }

    @Test
    fun `given key of item to remove when it is stored on db then response should be right with true value`() = runTest {

        coEvery { database.cacheItemDao().deleteById(key = "key") } returns 1

        cacheDatasource.remove(key = "key").shouldBeRight(true)
    }

    @Test
    fun `given key of item to remove when it is not stored on db then response should be right with false value`() = runTest {

        coEvery { database.cacheItemDao().deleteById(key = "key") } returns 0

        cacheDatasource.remove(key = "key").shouldBeRight(false)
    }
}