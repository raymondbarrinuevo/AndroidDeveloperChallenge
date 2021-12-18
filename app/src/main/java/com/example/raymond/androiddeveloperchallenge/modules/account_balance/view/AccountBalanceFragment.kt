package com.example.raymond.androiddeveloperchallenge.modules.account_balance.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.customview.ProgressDialog
import com.example.raymond.androiddeveloperchallenge.core.utils.Utils
import com.example.raymond.androiddeveloperchallenge.core.view.BaseAppFragment
import com.example.raymond.androiddeveloperchallenge.modules.account_balance.contract.AccountBalanceContract
import com.example.raymond.androiddeveloperchallenge.modules.account_balance.model.Balance
import com.example.raymond.androiddeveloperchallenge.modules.account_balance.presenter.AccountBalancePresenter
import kotlinx.android.synthetic.main.fragment_account_balance.*


open class AccountBalanceFragment :
    BaseAppFragment<AccountBalanceContract.Presenter<AccountBalanceContract.View>>(),
    AccountBalanceContract.View {


    var progressDialog: ProgressDialog? = null


    override fun createPresenterInstance(): AccountBalanceContract.Presenter<AccountBalanceContract.View> {
        return AccountBalancePresenter(context)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_account_balance
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRefresh.setOnClickListener {
            if (Utils().isInternetOn(context)) {
                progressDialog?.showProgress()
                presenter?.getAccountBalance()
            } else {
                Toast.makeText(context, getString(R.string.err_no_network), Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }

    override fun initViews() {
        presenter?.attachView(this)
        progressDialog = ProgressDialog(context)
        if (Utils().isInternetOn(context)) {
            progressDialog?.showProgress()
            presenter?.getAccountBalance()
        } else {
            Toast.makeText(context, getString(R.string.err_no_network), Toast.LENGTH_SHORT).show()
        }

    }

    override fun showAccountBalance(balance: Balance) {
        progressDialog?.hideProgress()
        tvCurrentBalance.text = "${balance.data.currency} ${balance.data.balance}"
    }

    override fun requestFail(message: String) {
        progressDialog?.hideProgress()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}