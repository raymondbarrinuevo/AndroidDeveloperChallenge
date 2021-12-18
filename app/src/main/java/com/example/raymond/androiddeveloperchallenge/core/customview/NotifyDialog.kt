package com.mobile.raymond.tindahan.core.customview

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import butterknife.BindView
import com.example.raymond.androiddeveloperchallenge.R
import com.mobile.raymond.tindahan.core.view.BaseDialog


class NotifyDialog : BaseDialog() {

    @BindView(R.id.tv_title)
    lateinit var tvTitle: TextView

    @BindView(R.id.tv_msg)
    lateinit var tvMessage: TextView

    @BindView(R.id.btn_cancel)
    lateinit var btnCancel: FrameLayout

    @BindView(R.id.btn_ok)
    lateinit var btnOk: FrameLayout

    @BindView(R.id.tv_cancel)
    lateinit var tvCancel: TextView

    @BindView(R.id.tv_ok)
    lateinit var tvOk: TextView

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

    fun showDialog(fragmentManager: androidx.fragment.app.FragmentManager, title: String?,
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

        initViews()
        initActions()
    }

//    override fun onConfigurationChanged(newConfig: Configuration?) {
//        super.onConfigurationChanged(newConfig)
//        unbinder.unbind()
//        frameLayout?.removeAllViews()
//        val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        frameLayout?.addView(inflater.inflate(getDialogLayout(), container, false))
//        unbinder = ButterKnife.bind(this, frameLayout!!)
//        frameLayout?.addView(view)
//
//        initViews()
//        initActions()
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initActions() {
        btnOk.setOnClickListener { v ->
            if (listener != null) {
                listener?.onRightButtonClick()
            }
            dismiss()
        }

        btnCancel.setOnClickListener { v ->
            if (listener != null) {
                listener?.onLeftButtonClick()
            }
            dismiss()
        }
    }

    private fun initViews() {
        if (title != null) {
            tvTitle.setText(title)
        }

        if (message != null) {
            tvMessage.setText(message)
        }

        if (positiveTitle != null) {
            tvOk.setText(positiveTitle)
        }

        if (negativeTitle != null) {
            tvCancel.setText(negativeTitle)
        }
        if (isChangeButtonPosition) {
            tvCancel.setTextColor(context?.getResources()?.getColor(R.color.orange_opacity_30)!!)
            tvOk.setTextColor(context?.getResources()?.getColor(R.color.dimgrey)!!)
        } else {
            tvCancel.setTextColor(context?.getResources()?.getColor(R.color.dimgrey)!!)
            tvOk.setTextColor(context?.getResources()?.getColor(R.color.orange_opacity_30)!!)
        }
        btnCancel.setVisibility(if (isShowCancel) View.VISIBLE else View.GONE)
    }
}