package com.example.raymond.androiddeveloperchallenge.network

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

class RxCallAdapterWrapper<R> : CallAdapter<R, Any> {
    private var retrofit: Retrofit? = null
    private var wrapped: CallAdapter<R, Any>? = null

    constructor(retrofit: Retrofit, wrapper: CallAdapter<R, Object>) {
        this.retrofit = retrofit
        this.wrapped = wrapped
    }

    override fun responseType(): Type? {
        return wrapped?.responseType()
    }

    @SuppressWarnings("unchecked")
    override fun adapt(call: Call<R>): Any? {
        val result = wrapped?.adapt(call)

        if (result is Single<*>) {
            result.onErrorResumeNext { throwable -> Single.error(asRetrofitException(throwable as Throwable)) }
        }

        if (result is Completable) {
            return result.onErrorResumeNext { throwable -> Completable.error(asRetrofitException(throwable as Throwable)) }
        }

        return result
    }

    private fun asRetrofitException(throwable: Throwable): RetrofitException {
        // We had non-200 http error
        if (throwable is HttpException) {
            val response = throwable.response()
            return RetrofitException().httpError(response?.raw()?.request?.url.toString(), response!!, retrofit)
        }
        // A network error happened
        return if (throwable is IOException) {
            RetrofitException().networkError(throwable)
        } else RetrofitException().unexpectedError(throwable)

        // We don't know what happened. We need to simply convert to an unknown error
    }

}