package com.example.raymond.androiddeveloperchallenge.modules.main.view


import androidx.fragment.app.Fragment
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.view.BaseActivity
import com.example.raymond.androiddeveloperchallenge.modules.account_balance.view.AccountBalanceFragment
import com.example.raymond.androiddeveloperchallenge.modules.main.contract.MainContract
import com.example.raymond.androiddeveloperchallenge.modules.main.presenter.MainPresenter
import java.util.*

class MainActivity : BaseActivity<MainContract.Presenter<MainContract.View>>(), MainContract.View {


    override fun createPresenterInstance(): MainContract.Presenter<MainContract.View> {
        return MainPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        setContent(AccountBalanceFragment())
    }

    private val mListBackStack = ArrayList<Fragment>()

    override fun setContent(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        val iterator = mListBackStack.iterator()
        while (iterator.hasNext()) {
            val fragmentInBackStack = iterator.next()
            if (fragmentInBackStack.javaClass.simpleName == fragment.javaClass.simpleName) {
                iterator.remove()
                break
            }
        }
        mListBackStack.add(fragment)
        transaction?.replace(R.id.mainContainer, fragment, fragment.javaClass.simpleName)
        transaction?.commit()
    }

    override fun showMessage(message: String) {

    }
}