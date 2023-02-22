package com.ab21.core.errors

/**
 * This class include all possible error that the application expose
 */
sealed class Error {

    /**
     * The base class of network errors
     */
    sealed class NetworkError: Error() {
        object NotFound : NetworkError()
        object InternalError : NetworkError()
        object MalformedJson : NetworkError()
        object Timeout: NetworkError()
        object Unknown: NetworkError()
    }

    /**
     * The base class of database errors
     */
    sealed class DatabaseError: Error() {
        object ReadingError : DatabaseError()
        object WritingError : DatabaseError()
        object DeletingError : DatabaseError()
    }
}