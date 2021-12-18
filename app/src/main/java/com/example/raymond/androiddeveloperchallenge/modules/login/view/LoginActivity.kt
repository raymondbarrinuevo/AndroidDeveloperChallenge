package com.example.raymond.androiddeveloperchallenge.modules.login.view

import android.content.Intent
import android.widget.Toast
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.customview.ProgressDialog
import com.example.raymond.androiddeveloperchallenge.core.utils.Utils
import com.example.raymond.androiddeveloperchallenge.core.view.BaseActivity
import com.example.raymond.androiddeveloperchallenge.modules.login.contract.LoginContract
import com.example.raymond.androiddeveloperchallenge.modules.login.presenter.LoginPresenter
import com.example.raymond.androiddeveloperchallenge.modules.main.view.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<LoginContract.Presenter<LoginContract.View>>(),
    LoginContract.View {

    var progressDialog: ProgressDialog? = null

    override fun createPresenterInstance(): LoginContract.Presenter<LoginContract.View> {
        return LoginPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initViews() {
        getPresenter()?.attachView(this)

        tv_username.setText("mobile.test@finxp.com")
        tv_password.setText("MobileTest123!$")

        btn_login.setOnClickListener {
            if (Utils().isInternetOn(this)) {
                login()
            } else {
                Toast.makeText(this, getString(R.string.err_no_network), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun login() {
        val email = tv_username.text.toString()
        val pass = tv_password.text.toString()
        if (getPresenter()?.isLoginValid(email, pass)!!) {
            progressDialog = ProgressDialog(this)
            progressDialog?.showProgress()
            getPresenter()?.login(email, pass)
        } else {
            Toast.makeText(
                this,
                getString(R.string.message_email_or_pass_empty),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun onLoginSuccess() {
        progressDialog?.hideProgress()
        goToMainActivity()
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        this.finish()
    }

    override fun onLoginFail(message: String) {
        progressDialog?.hideProgress()
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}