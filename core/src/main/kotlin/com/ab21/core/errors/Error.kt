package com.ab21.core.errors

/**
 * This class include all possible error that the application expose
 */
sealed class Error {

    /**
     * The base class of network errors
     */
    sealed class NetworkError: Error() {
        data object NotFound : NetworkError()
        data object InternalError : NetworkError()
        data object MalformedJson : NetworkError()
        data object Timeout: NetworkError()
        data object Unknown: NetworkError()
    }
}