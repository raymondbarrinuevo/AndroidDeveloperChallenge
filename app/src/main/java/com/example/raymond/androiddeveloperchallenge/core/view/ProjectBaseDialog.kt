package com.example.raymond.androiddeveloperchallenge.core.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.ColorDrawable

abstract class ProjectBaseDialog : Dialog {

    constructor(context: Context?)
            : super(context!!) {
        this.requestWindowFeature(1)
        this.window!!.setBackgroundDrawable(ColorDrawable(0))
        this.initDialog()
    }

    protected abstract fun initDialog()
}