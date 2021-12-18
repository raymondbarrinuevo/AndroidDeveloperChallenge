package com.mobile.raymond.tindahan.core.view

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import butterknife.ButterKnife
import butterknife.Unbinder

abstract class BaseDialog : androidx.fragment.app.DialogFragment() {

    protected var container: ViewGroup? = null
    protected var frameLayout: FrameLayout? = null
    protected lateinit var unbinder: Unbinder

    protected abstract fun getDialogLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        frameLayout = FrameLayout(requireActivity())
        frameLayout?.addView(inflater.inflate(getDialogLayout(), container, false))
        this.container = container
        ButterKnife.bind(this, frameLayout!!)
        return frameLayout
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    override fun onDestroyView() {
        val dialog = dialog
        // handles https://code.google.com/p/android/issues/detail?id=17423
        if (dialog != null && retainInstance) {
            dialog.setDismissMessage(null)
        }
        super.onDestroyView()
    }
}