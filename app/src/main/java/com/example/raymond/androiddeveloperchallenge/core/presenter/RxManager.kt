package com.example.raymond.androiddeveloperchallenge.core.presenter

import android.content.Context
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.example.raymond.androiddeveloperchallenge.network.Request
import io.reactivex.Completable
import io.reactivex.disposables.Disposable

class RxManager {
    private var disposables: CompositeDisposable? = null
    private var mContext: Context? = null

    constructor(mContext: Context?) {
        this.disposables = CompositeDisposable()
        this.mContext = mContext
    }

    fun getDisposables(): CompositeDisposable? {
        return disposables
    }

    fun <T> execute(request: Request<T>) {
        val disposable = request.getRequest()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(request::handleResponse, request::onError)

        disposables?.add(disposable)
    }

    fun <T> execute(request: Request<T>, isShowProgressDialog: Boolean) {
        if (isShowProgressDialog) {
        }
        execute(request)
    }

    fun run(action: Completable, listener: ICompletable) {
        val disposable = action
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(listener::onSuccess, listener::onError)

        disposables?.add(disposable)
    }

}