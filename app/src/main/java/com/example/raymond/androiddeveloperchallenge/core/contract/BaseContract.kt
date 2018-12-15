package com.example.raymond.androiddeveloperchallenge.core.contract

import com.example.raymond.androiddeveloperchallenge.modules.pinboard.model.PinBoard

interface BaseContract {

    interface View {
        fun showMessage(message: String)

        fun showRawData(raw: List<PinBoard>)
    }

    interface Presenter {

        fun attachView(view: View)

        fun detachView()

        fun clear()

        fun getRawData()
    }
}