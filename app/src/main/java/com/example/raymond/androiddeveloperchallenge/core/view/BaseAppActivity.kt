package com.example.raymond.androiddeveloperchallenge.core.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.raymond.androiddeveloperchallenge.core.utils.FragmentController
import com.mobile.raymond.tindahan.core.customview.NotifyDialog

abstract class BaseAppActivity : AppCompatActivity() {

    private var mFragmentController: FragmentController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun initViews()

    protected fun getContainerId(): Int {
        return 0
    }

    fun getFragmentController(): FragmentController? {
        return mFragmentController
    }

    /**
     * This is method switch screen on container from [.getContainerId]
     *
     * @param baseFragment
     * @param option
     */
    @Throws(NullPointerException::class)
    fun switchScreenOnContainer(baseFragment: BaseFragment, option: FragmentController.Option) {
        val fragmentController = getFragmentController()
        if (fragmentController != null) {
            fragmentController.switchFragmentWithInstance(baseFragment, option)
        } else {
            throw NullPointerException("Fragment controller null")
        }
    }

    fun showNotifyDialog(title: String, message: String, isShowCancelButton: Boolean) {
        showNotifyDialog(title, message, null, null, isShowCancelButton, false, null)
    }

    fun showNotifyDialog(title: String, message: String,
                         callback: NotifyDialog.OnNotifyCallback, isShowCancelButton: Boolean) {
        showNotifyDialog(title, message,
                null, null, isShowCancelButton, false, callback)
    }

    fun showNotifyDialog(title: String, message: String,
                         callback: NotifyDialog.OnNotifyCallback, positiveTitle: String, negativeTitle: String) {
        showNotifyDialog(title, message, positiveTitle,
                negativeTitle, true, false, callback)
    }

    fun showNotifyDialog(title: String, message: String,
                         callback: NotifyDialog.OnNotifyCallback, positiveTitle: String, negativeTitle: String, isChangeButtonPosition: Boolean) {
        showNotifyDialog(title, message, positiveTitle,
                negativeTitle, true, isChangeButtonPosition, callback)
    }

    fun showNotifyDialog(title: String, message: String,
                         callback: NotifyDialog.OnNotifyCallback, positiveTitle: String) {
        showNotifyDialog(title, message, positiveTitle,
                null, false, false, callback)
    }

    private fun showNotifyDialog(title: String, message: String, positiveTitle: String?,
                                 negativeTitle: String?, isShowCancel: Boolean, isChangeButtonPosition: Boolean, callback: NotifyDialog.OnNotifyCallback?) {
        val notifyDialog = NotifyDialog()
        notifyDialog.showDialog(supportFragmentManager, title, message, positiveTitle,
                negativeTitle, isShowCancel, isChangeButtonPosition, callback)
    }
}