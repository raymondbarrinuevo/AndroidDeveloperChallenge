package com.example.raymond.androiddeveloperchallenge.modules.login.contract

import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract

class LoginContract {

    interface View : BaseContract.View {
        fun onLoginSuccess()

        fun onLoginFail(message: String)
    }

    interface Presenter<V : LoginContract.View> : BaseContract.Presenter {

        fun attachView(view: V)

        fun login(userName: String, password: String)

        fun isLoginValid(email: String, pass: String): Boolean
    }

}