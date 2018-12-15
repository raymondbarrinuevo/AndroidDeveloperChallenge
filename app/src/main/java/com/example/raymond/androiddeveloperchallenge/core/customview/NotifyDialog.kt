package com.example.raymond.androiddeveloperchallenge.core.customview

import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.view.View
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.view.BaseDialog
import kotlinx.android.synthetic.main.dialog_notify.*

class NotifyDialog : BaseDialog() {

    private var title: String? = null
    private var message: String? = null
    private var isShowCancel: Boolean = false
    private var listener: OnNotifyCallback? = null
    private var positiveTitle: String? = null
    private var negativeTitle: String? = null
    private var isChangeButtonPosition: Boolean = false

    interface OnNotifyCallback {
        fun onLeftButtonClick()

        fun onRightButtonClick(vararg obj: Any)
    }

    fun showDialog(fragmentManager: FragmentManager, title: String?,
                   message: String?, positiveTitle: String?, negativeTitle: String?,
                   isShowCancel: Boolean, isChangeButtonPosition: Boolean, listener: OnNotifyCallback?) {
        val notifyDialog = NotifyDialog()

        notifyDialog.title = title
        notifyDialog.message = message
        notifyDialog.isShowCancel = isShowCancel
        notifyDialog.positiveTitle = positiveTitle
        notifyDialog.negativeTitle = negativeTitle
        notifyDialog.listener = listener
        notifyDialog.isChangeButtonPosition = isChangeButtonPosition
        notifyDialog.retainInstance = true

        notifyDialog.show(fragmentManager, NotifyDialog::class.java.simpleName)
    }

    override fun getDialogLayout(): Int {
        return R.layout.dialog_notify
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initActions() {
        btn_ok.setOnClickListener({ v ->
            if (listener != null) {
                listener?.onRightButtonClick()
            }
            dismiss()
        })

        btn_ok.setOnClickListener({ v ->
            if (listener != null) {
                listener?.onLeftButtonClick()
            }
            dismiss()
        })
    }

    private fun initViews() {
        if (title != null) {
            tv_title.text = title
        }

        if (message != null) {
            tv_msg.text = message
        }

        if (positiveTitle != null) {
            tv_ok.text = positiveTitle
        }

        if (negativeTitle != null) {
            tv_cancel.text = negativeTitle
        }
        tv_cancel.setVisibility(if (isShowCancel) View.VISIBLE else View.GONE)
    }
}