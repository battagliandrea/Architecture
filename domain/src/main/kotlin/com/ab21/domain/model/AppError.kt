package com.ab21.domain.model

import arrow.core.Either
import arrow.core.Either.Right
import arrow.core.Either.Left

sealed class AppError(cause: Throwable?) : Throwable(cause) {

  sealed class Remote(cause: Throwable?) : AppError(cause) {
    data class NetworkError(override val cause: Throwable) : Remote(cause)

    data class ServerError(
      val errorMessage: String,
      val statusCode: Int,
      override val cause: Throwable? = null,
    ) : Remote(cause)

  }

  sealed class Local(cause: Throwable?) : AppError(cause) {
    data class DatabaseError(override val cause: Throwable) : AppError(cause)
  }

  data class UnexpectedError(
    val errorMessage: String,
    override val cause: Throwable,
  ) : AppError(cause)
}

typealias Option<T> = Either<Unit, T>
typealias DomainResult<T> = Either<AppError, T>

@Suppress("NOTHING_TO_INLINE")
inline fun <T> T?.toOption(): Option<T> = this?.let { Right(it) } ?: Left(Unit)

@Suppress("NOTHING_TO_INLINE")
inline fun <R> AppError.leftResult(): Either<AppError, R> = Left(this)

@Suppress("NOTHING_TO_INLINE")
inline fun <R> R.rightResult(): Either<AppError, R> = Right(this)