package com.example.raymond.androiddeveloperchallenge.modules.main.contract

import android.support.v4.app.Fragment
import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract

class MainContract {

    interface View : BaseContract.View {
        fun setContent(fragment: Fragment)

    }

    interface Presenter : BaseContract.Presenter
}
