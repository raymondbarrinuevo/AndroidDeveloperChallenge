package com.example.raymond.androiddeveloperchallenge.modules.main.contract


import androidx.fragment.app.Fragment
import com.example.raymond.androiddeveloperchallenge.core.contract.BaseContract

class MainContract {

    interface View : BaseContract.View {
        fun setContent(fragment: Fragment)

    }

    interface Presenter<V : View> : BaseContract.Presenter {

        fun attachView(view: V)
    }


}
