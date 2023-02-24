package com.ab21.data.network.datasource

import com.ab21.data.datasource.INetworkDatasource
import com.ab21.data.network.env.Environment
import com.ab21.data.network.extensions.enqueueResponse
import com.ab21.data.network.service.PokeApi
import com.ab21.domain.model.AppError
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit.SECONDS

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class NetworkDatasourceTest {

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(5, SECONDS)
        .readTimeout(5, SECONDS)
        .writeTimeout(5, SECONDS)
        .build()

    private val contentType = "application/json".toMediaType()

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val apiService = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()
        .create(PokeApi::class.java)

    private val environment: Environment = mockk {
        every { imagesBaseUrl } returns "/"
    }

    private lateinit var networkDatasource: INetworkDatasource

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        networkDatasource = NetworkDatasource(environment, apiService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    /*
    * /pokemon
    */
    @Test
    fun `given pokemon list api 200 response then check successful response`() = runTest {
        mockWebServer.enqueueResponse("pokemon_200.json", 200)

        networkDatasource.pokemon(0, 20).shouldBeRight()
    }

    @Test
    fun `given pokemon list api 200 malformed response then result is malformed json state`() = runTest {
        mockWebServer.enqueueResponse("pokemon_200_malformed.json", 200)

        networkDatasource.pokemon(0, 20)
            .shouldBeLeft()
            .shouldBeTypeOf<AppError.Json.DecodingError>()
    }

    @Test
    fun `given pokemon list api 404 response then result is not found error state`() = runTest {
        mockWebServer.enqueueResponse("response_400.txt", 400)

        networkDatasource.pokemon(0, 20)
            .shouldBeLeft()
            .shouldBeTypeOf<AppError.Remote.ServerError>()
            .statusCode shouldBe 400
    }

    @Test
    fun `given pokemon list api 500 response then result is service internal error state`() = runTest {
        mockWebServer.enqueueResponse("response_500.txt", 500)

        networkDatasource.pokemon(0, 20)
            .shouldBeLeft()
            .shouldBeTypeOf<AppError.Remote.ServerError>()
            .statusCode shouldBe 500
    }

    /*
    * /pokemon/{id}
    */
    @Test
    fun `given pokemon details api 200 response then check successful response`() = runTest {
        mockWebServer.enqueueResponse("pokemon_details_200.json", 200)

        networkDatasource.pokemon(id = 56).shouldBeRight()
    }

    @Test
    fun `given pokemon details api 200 malformed response then result is malformed json state`() = runTest {
        mockWebServer.enqueueResponse("pokemon_details_200_malformed.json", 200)

        networkDatasource.pokemon(id = 56)
            .shouldBeLeft()
            .shouldBeTypeOf<AppError.Json.DecodingError>()
    }

    @Test
    fun `given pokemon details api 404 response then result is not found error state`() = runTest {
        mockWebServer.enqueueResponse("response_400.txt", 400)

        networkDatasource.pokemon(id = 56)
            .shouldBeLeft()
            .shouldBeTypeOf<AppError.Remote.ServerError>()
            .statusCode shouldBe 400
    }

    @Test
    fun `given pokemon details  api 500 response then result is service internal error state`() = runTest {
        mockWebServer.enqueueResponse("response_500.txt", 500)

        networkDatasource.pokemon(id = 56)
            .shouldBeLeft()
            .shouldBeTypeOf<AppError.Remote.ServerError>()
            .statusCode shouldBe 500
    }
}