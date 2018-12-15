package com.example.raymond.androiddeveloperchallenge.network

import io.reactivex.Flowable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.io.IOException
import java.util.logging.Level
import java.util.logging.Logger

abstract class Request<U>() : Subscriber<U> {


    override fun onError(throwable: Throwable) {
        throwable.printStackTrace()
        if (throwable is RetrofitException) {
            if (throwable.getKind() === RetrofitException.Kind.HTTP) {
                Logger.getLogger("ERROR HTTP: ").log(Level.SEVERE, throwable.message)
                this.handleError(ApiConstant().INTERNAL_SERVER_ERROR, throwable)
            } else if (throwable.getKind() === RetrofitException.Kind.NETWORK) {
                Logger.getLogger("ERROR NETWORK: ").log(Level.SEVERE, throwable.message)
                this.handleError(ApiConstant().NO_NETWORK, throwable)
            } else if (throwable.getKind() === RetrofitException.Kind.UNEXPECTED) {
                Logger.getLogger("ERROR UNEXPECTED: ").log(Level.SEVERE, throwable.message)
                this.handleError(ApiConstant().NO_NETWORK, throwable)
            }

        } else if (throwable is IOException) {
            Logger.getLogger("IOException: ").log(Level.SEVERE, throwable.message)
            this.handleError(ApiConstant().NO_NETWORK, throwable)
        } else if (throwable is RuntimeException) {
            Logger.getLogger("RuntimeException: ").log(Level.SEVERE, throwable.message)
            this.handleError(ApiConstant().RUN_TIME_EXCEPTION, throwable)
        }
    }

    override fun onNext(response: U) {
        //TODO:
    }

    override fun onComplete() {
        //TODO:
    }

    override fun onSubscribe(s: Subscription) {
        //TODO:
    }

    abstract fun getRequest(): Flowable<U>

    abstract fun handleResponse(response: U)

    abstract fun handleError(errCode: Int, throwable: Throwable)
}