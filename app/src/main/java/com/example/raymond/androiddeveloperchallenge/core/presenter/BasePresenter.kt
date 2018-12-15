package com.example.raymond.androiddeveloperchallenge.core.presenter

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract

import com.example.raymond.androiddeveloperchallenge.network.ApiManager
import com.example.raymond.androiddeveloperchallenge.network.BaseProjectAPI
import com.example.raymond.androiddeveloperchallenge.network.Request

abstract class BasePresenter : BaseContract.Presenter {

    private var context: Context? = null
    private var view: BaseContract.View? = null
    private var api: BaseProjectAPI? = null
    protected var rxManager: RxManager? = null

    constructor(context: Context?) {
        this.context = context
        this.api = ApiManager().getInstance()?.baseProjectAPI
        this.rxManager = RxManager(context)
    }

    protected fun getContext(): Context? {
        return context
    }

    override fun attachView(view: BaseContract.View) {
        this.view = view
    }

    override fun detachView() {
        if (view != null) {
            view = null
        }
    }

    override fun clear() {
        rxManager?.getDisposables()?.clear()
    }

    protected fun getView(): BaseContract.View? {
        return view
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