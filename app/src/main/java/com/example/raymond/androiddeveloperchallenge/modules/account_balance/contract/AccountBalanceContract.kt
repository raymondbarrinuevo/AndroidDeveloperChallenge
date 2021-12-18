package com.example.raymond.androiddeveloperchallenge.modules.account_balance.contract

import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract
import com.example.raymond.androiddeveloperchallenge.modules.account_balance.model.Balance

class AccountBalanceContract {

    interface View : BaseContract.View {
        fun showAccountBalance(balance: Balance)

        fun requestFail(message: String)
    }

    interface Presenter<V : AccountBalanceContract.View> : BaseContract.Presenter {
        fun attachView(view: V)

        fun getAccountBalance()

    }
}