package com.example.raymond.androiddeveloperchallenge.core.presenter

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract

import com.example.raymond.androiddeveloperchallenge.network.ApiManager
import com.example.raymond.androiddeveloperchallenge.network.BaseProjectAPI
import com.example.raymond.androiddeveloperchallenge.network.Request
import com.google.gson.Gson

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter {

    private var context: Context? = null
    private var api: BaseProjectAPI? = null
    protected var rxManager: RxManager? = null
    val gson: Gson = Gson()
    private lateinit var view: V

    constructor(context: Context?) {
        this.context = context
        this.api = ApiManager(context!!).getInstance()?.baseProjectAPI
        this.rxManager = RxManager(context)
    }

    protected fun getContext(): Context? {
        return context
    }

    override fun detachView() {

    }

    override fun clear() {
        rxManager?.getDisposables()?.clear()
    }

    protected fun getApi(): BaseProjectAPI? {
        return api
    }

    fun <T> execute(request: Request<T>) {
        rxManager?.execute(request)
    }

    protected fun <T> execute(request: Request<T>, isShowProgressDialog: Boolean) {
        if (isShowProgressDialog) {
            rxManager?.execute(request, isShowProgressDialog)
        } else {
            execute(request)
        }
    }

}