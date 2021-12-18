package com.example.raymond.androiddeveloperchallenge.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.mobile.raymond.tindahan.core.view.BaseDialog

abstract class BaseFragment : Fragment() {

    protected abstract fun getLayoutId(): Int

    protected var dialog: BaseDialog? = null
    protected var frameLayout: FrameLayout? = null
    protected var container: ViewGroup? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        frameLayout = FrameLayout(requireActivity())
        this.container = container
        frameLayout?.addView(inflater.inflate(getLayoutId(), container, false))
        initViews()

        return frameLayout
    }

    protected abstract fun initViews()

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun onResumeFragment() {

    }

    protected fun isActivityAvailable(): Boolean {
        return if (activity == null || activity!!.isFinishing) {
            false
        } else true
    }

    protected fun getBaseActivity(): BaseAppActivity? {
        val activity = activity
        return if (isActivityAvailable() && activity is BaseAppActivity) {
            activity as BaseAppActivity?
        } else {
            null
        }
    }

    fun onBackPressed(): Boolean {
        return false
    }

    fun showNotifyDialog(title: String, message: String, isShowCancelButton: Boolean) {
        getBaseActivity()?.showNotifyDialog(title, message, isShowCancelButton)
    }
}