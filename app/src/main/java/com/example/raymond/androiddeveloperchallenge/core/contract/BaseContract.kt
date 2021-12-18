package com.example.raymond.androiddeveloperchallenge.core.contract

interface BaseContract {

    interface View {
        fun showMessage(message: String)

    }

    interface Presenter {

        fun detachView()

        fun clear()
    }
}