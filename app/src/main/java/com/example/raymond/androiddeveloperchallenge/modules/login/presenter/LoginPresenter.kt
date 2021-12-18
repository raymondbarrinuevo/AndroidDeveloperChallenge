package com.example.raymond.androiddeveloperchallenge.modules.login.presenter

import android.content.Context
import com.example.raymond.androiddeveloperchallenge.core.LocalSharedPreferences
import com.example.raymond.androiddeveloperchallenge.core.presenter.BasePresenter
import com.example.raymond.androiddeveloperchallenge.modules.login.contract.LoginContract
import com.example.raymond.androiddeveloperchallenge.modules.login.model.Token
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(context: Context) : BasePresenter<LoginContract.View>(context),
    LoginContract.Presenter<LoginContract.View> {

    private lateinit var view: LoginContract.View

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun login(userName: String, password: String) {
        getApi()?.login(userName, password)?.enqueue(object : Callback<String> {

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val code = response.code()
                val message = response.message()
                if (code == 200) {
                    val token = gson.fromJson<Token>(response.body(), Token::class.java)
                    LocalSharedPreferences().saveToken(getContext()!!, gson, token)
                    view.onLoginSuccess()
                } else {
                    view.onLoginFail(message)
                }

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                view.onLoginFail(t.message!!)
            }
        })
    }

    override fun isLoginValid(email: String, pass: String): Boolean {
        return !(email == null || email.isEmpty() || pass == null || pass.isEmpty())

    }

}