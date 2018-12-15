package com.example.raymond.androiddeveloperchallenge.core.view

import android.os.Bundle
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract

abstract class BaseAppFragment<P : BaseContract.Presenter> : BaseFragment(), BaseContract.View {

    protected var presenter: P? = null

    protected abstract fun createPresenterInstance(): P

    private var callBack: CallBack? = null

    interface CallBack {
        fun onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = createPresenterInstance()
        if (presenter != null) {
            presenter?.attachView(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (presenter != null) {
            presenter?.detachView()
            presenter?.clear()
        }
    }

    override fun showMessage(message: String) {
        showNotifyDialog(getString(R.string.dialog_message), message, false)
    }

    protected fun setOnBackPressed(callBack: CallBack) {
        this.callBack = callBack
    }


}