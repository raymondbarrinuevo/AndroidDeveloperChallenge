package com.example.raymond.androiddeveloperchallenge.core.view

import android.os.Bundle
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract


abstract class BaseActivity<P : BaseContract.Presenter> : BaseAppActivity(), BaseContract.View {

    private var presenter: P? = null

    protected abstract fun createPresenterInstance(): P

    fun getPresenter(): P? {
        return presenter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        presenter = createPresenterInstance()
        super.onCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (presenter != null) {
            presenter?.detachView()
            presenter?.clear()
        }
    }

    override fun showMessage(message: String) {
        showNotifyDialog(getString(R.string.dialog_message), message, false)
    }
}