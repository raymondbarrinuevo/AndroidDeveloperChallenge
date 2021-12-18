package com.example.raymond.androiddeveloperchallenge.modules.account_balance.presenter

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.core.presenter.BasePresenter
import com.example.raymond.androiddeveloperchallenge.modules.account_balance.contract.AccountBalanceContract
import com.example.raymond.androiddeveloperchallenge.modules.account_balance.model.Balance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountBalancePresenter(activity: Context?) :
    BasePresenter<AccountBalanceContract.View>(activity),
    AccountBalanceContract.Presenter<AccountBalanceContract.View> {

    private lateinit var view: AccountBalanceContract.View

    override fun attachView(view: AccountBalanceContract.View) {
        this.view = view
    }

    override fun getAccountBalance() {
        getApi()?.getBalance()?.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val code = response.code()
                val message = response.message()

                if (code == 200) {
                    val balance = gson.fromJson<Balance>(response.body(), Balance::class.java)
                    view.showAccountBalance(balance)
                } else {
                    view.requestFail(message)
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.requestFail(t.message!!)
            }
        })
    }
}