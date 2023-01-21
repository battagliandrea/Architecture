package com.ab21.data.network.extensions

import arrow.core.Either
import com.ab21.data.network.model.toDomain
import com.ab21.domain.errors.Error.NetworkError
import retrofit2.Response
import java.net.HttpURLConnection

suspend fun <DTO, MODEL> enqueueResponse(call: suspend () -> Response<DTO>, mapper: (body: DTO?) -> MODEL): Either<NetworkError, MODEL> =
    try {
        val response = call.invoke()
        if(response.isSuccessful){
            Either.Right(mapper.invoke(response.body()))
        } else {
            Either.Left(response.toError())
        }
    } catch (e: Exception){
        when (e) {
            is IllegalArgumentException -> Either.Left(NetworkError.MalformedJson)
            else -> Either.Left(NetworkError.Unknown)
        }
    }


fun <T>Response<T>.toError() =
    when(this.code()) {
        HttpURLConnection.HTTP_NOT_FOUND -> NetworkError.NotFound
        HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkError.InternalError
        else -> NetworkError.Unknown
    }
