package com.example.raymond.androiddeveloperchallenge.modules.main.presenter

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract
import com.example.raymond.androiddeveloperchallenge.core.presenter.BasePresenter
import com.example.raymond.androiddeveloperchallenge.modules.main.contract.MainContract
import com.example.raymond.androiddeveloperchallenge.network.Request
import io.reactivex.Flowable

class MainPresenter(context: Context) : BasePresenter<MainContract.View>(context),
    MainContract.Presenter<MainContract.View> {

    override fun attachView(view: MainContract.View) {

    }
}