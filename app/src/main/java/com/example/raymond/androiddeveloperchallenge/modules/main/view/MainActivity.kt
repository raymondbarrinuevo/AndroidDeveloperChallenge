package com.example.raymond.androiddeveloperchallenge.modules.main.view

import android.support.v4.app.Fragment
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract
import com.example.raymond.androiddeveloperchallenge.core.view.BaseActivity
import com.example.raymond.androiddeveloperchallenge.modules.main.contract.MainContract
import com.example.raymond.androiddeveloperchallenge.modules.main.presenter.MainPresenter
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.model.PinBoard
import com.example.raymond.androiddeveloperchallenge.modules.pinboard.view.PinBoardFragment
import java.util.*

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    override fun showRawData(raw: List<PinBoard>) {

    }

    override fun createPresenterInstance(): MainContract.Presenter {
        return MainPresenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {
        setContent(PinBoardFragment())
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

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun popBackStackCustom(currentFragment: Fragment, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainContainer, fragment, fragment.javaClass.simpleName)
        transaction.commit()
        mListBackStack.remove(currentFragment)
    }
}