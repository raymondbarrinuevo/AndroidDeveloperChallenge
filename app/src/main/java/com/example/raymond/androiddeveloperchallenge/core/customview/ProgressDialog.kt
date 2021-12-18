package com.example.raymond.androiddeveloperchallenge.core.customview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.view.ProjectBaseDialog
import kotlinx.android.synthetic.main.progress_view.*


class ProgressDialog(context: Context?) : ProjectBaseDialog(context) {

    override fun initDialog() {
        val view = LayoutInflater.from(context).inflate(R.layout.progress_view, null as ViewGroup?)
        this.setContentView(view)
        this.setCanceledOnTouchOutside(false)
        this.setCancelable(false)
    }

    fun showProgress() {
        this.show()
        progress_avi.smoothToShow()
    }

    fun hideProgress() {
        progress_avi.smoothToHide()
        this.dismiss()
    }
}