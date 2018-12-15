package com.example.raymond.androiddeveloperchallenge.network

import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException

class RetrofitException() : RuntimeException() {

    fun httpError(url: String, response: Response<*>, retrofit: Retrofit?): RetrofitException {
        val message = response.code().toString() + " " + response.message()
        return RetrofitException(message, null, url, response, Kind.HTTP, retrofit)
    }

    fun networkError(exception: IOException): RetrofitException {
        return RetrofitException(exception.message, exception, "", null, Kind.NETWORK, null)
    }

    fun unexpectedError(exception: Throwable): RetrofitException {
        return RetrofitException(exception.message, exception, "", null, Kind.UNEXPECTED, null)
    }

    /** Identifies the event kind which triggered a [RetrofitException].  */
    enum class Kind {
        /** An [IOException] occurred while communicating to the server.  */
        NETWORK,
        /** A non-200 HTTP status code was received from the server.  */
        HTTP,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }

    private var url: String = ""
    private var response: Response<*>? = null
    private var kind: Kind? = null
    private var retrofit: Retrofit? = null

    constructor(message: String?, cause: Throwable?, url: String, response: Response<*>?, kind: Kind?, retrofit: Retrofit?) : this() {
        this.url = url
        this.response = response
        this.kind = kind
        this.retrofit = retrofit
    }


    /** The request URL which produced the error.  */
    fun getUrl(): String {
        return url
    }

    /** Response object containing status code, headers, body, etc.  */
    fun getResponse(): Response<*>? {
        return response
    }

    /** The event kind which triggered this error.  */
    fun getKind(): Kind? {
        return kind
    }

    /** The Retrofit this request was executed on  */
    fun getRetrofit(): Retrofit? {
        return retrofit
    }

    /**
     * HTTP response body converted to specified `type`. `null` if there is no
     * response.
     *
     * @throws IOException if unable to convert the body to the specified `type`.
     */
    @Throws(IOException::class)
    fun <T> getErrorBodyAs(type: Class<T>): T? {
        if (response == null || response?.errorBody() == null) {
            return null
        }
        val converter = retrofit?.responseBodyConverter<T>(type, arrayOfNulls(0))
        return converter?.convert(response?.errorBody()!!)
    }
}