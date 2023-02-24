package com.ab21.core.model

import arrow.core.Either
import arrow.core.Either.Right
import arrow.core.Either.Left

sealed class AppError(cause: Throwable?) : Throwable(cause) {

  /*
   + Errors related to remote datasource
   */
  sealed class Remote(cause: Throwable?) : AppError(cause) {

    data class NetworkError(override val cause: Throwable) : Remote(cause)

    data class ServerError(
      val errorMessage: String,
      val statusCode: Int,
      override val cause: Throwable? = null,
    ) : Remote(cause)
  }

  /*
   + Errors related to json
   */
  sealed class Json(cause: Throwable?) : AppError(cause) {
    data class DecodingError(override val cause: Throwable) : AppError(cause)
  }

  /*
   + Errors related to cache datasource
   */
  sealed class Cache(cause: Throwable?) : AppError(cause) {
    data class ReadingError(override val cause: Throwable) : AppError(cause)
    data class WritingError(override val cause: Throwable) : AppError(cause)
    data class DeletingError(override val cause: Throwable) : AppError(cause)
  }

  /*
   + Unexpected errors
   */
  data class UnexpectedError(
    val errorMessage: String,
    override val cause: Throwable,
  ) : AppError(cause)
}

typealias Option<T> = Either<Unit, T>
typealias Result<T> = Either<AppError, T>

@Suppress("NOTHING_TO_INLINE")
inline fun <T> T?.toOption(): Option<T> = this?.let { Right(it) } ?: Left(Unit)

@Suppress("NOTHING_TO_INLINE")
inline fun <R> AppError.leftResult(): Either<AppError, R> = Left(this)

@Suppress("NOTHING_TO_INLINE")
inline fun <R> R.rightResult(): Either<AppError, R> = Right(this)