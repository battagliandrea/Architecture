package com.ab21.data.network.extensions

import com.ab21.domain.model.AppError.Json
import com.ab21.domain.model.AppError.Remote
import com.ab21.domain.model.Result
import com.ab21.domain.model.leftResult
import com.ab21.domain.model.rightResult
import retrofit2.Response

suspend fun <DTO, MODEL> enqueueResponse(call: suspend () -> Response<DTO>, mapper: (body: DTO?) -> MODEL): Result<MODEL> =
    try {
        val response = call.invoke()
        if(response.isSuccessful){
            mapper.invoke(response.body()).rightResult()
        } else {
            response.toError().leftResult()
        }
    } catch (e: Exception){
        when (e) {
            is IllegalArgumentException -> Json.DecodingError(e)
            else -> Remote.NetworkError(e)
        }.leftResult()
    }


fun <T>Response<T>.toError() =
    Remote.ServerError(
        errorMessage = this.message(),
        statusCode = this.code()
    )

