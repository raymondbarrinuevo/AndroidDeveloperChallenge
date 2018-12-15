package com.example.raymond.androiddeveloperchallenge.core.presenter

interface ICompletable {
    abstract fun onSuccess()

    abstract fun onError(throwable: Throwable)
}