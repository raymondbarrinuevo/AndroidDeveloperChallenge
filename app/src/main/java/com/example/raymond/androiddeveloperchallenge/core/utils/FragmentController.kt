package com.example.raymond.androiddeveloperchallenge.core.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class FragmentController {

    val EXISTS_FRAGMENT_IN_BACK_STACK = -1
    val UNKNOWN_INSTANCE_FRAGMENT = -1
    private var mFragmentManager: FragmentManager? = null
    private var mLayoutContainerId: Int = 0

    fun FragmentController(fragmentManager: FragmentManager, @IdRes layoutContainer: Int) {
        mFragmentManager = fragmentManager
        mLayoutContainerId = layoutContainer
    }

    fun switchFragmentWithInstance(fragmentInstance: Fragment?, option: Option): Int? {
        if (this.isFragmentCurrentExists(option.tag)) {
            return EXISTS_FRAGMENT_IN_BACK_STACK
        }
        var isExistsBackStack: Boolean? = mFragmentManager?.popBackStackImmediate(option.tag, 0)
        if (isExistsBackStack == false) {
            if (fragmentInstance == null) {
                return UNKNOWN_INSTANCE_FRAGMENT
            }

            val transaction = mFragmentManager?.beginTransaction()
            if (option.isUseAnimation) {
                //                add Animations here
                //                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out, R.anim.slide_left_in, R.anim.slide_right_out);
            }

            if (option.isTransactionReplace) {
                transaction?.replace(mLayoutContainerId, fragmentInstance, option.tag)
            } else {
                transaction?.add(mLayoutContainerId, fragmentInstance, option.tag)
            }

            if (option.isAddBackStack) {
                transaction?.addToBackStack(option.tag)
            }
            return transaction?.commit()
        }
        return EXISTS_FRAGMENT_IN_BACK_STACK
    }

    fun isFragmentCurrentExists(tag: String?): Boolean {
        var ret = false
        val countFragmentInBackStack = mFragmentManager?.getBackStackEntryCount()

        if (!countFragmentInBackStack!!.equals(0)) {
            val currentEntry = mFragmentManager?.getBackStackEntryAt(countFragmentInBackStack!!.minus(1))
            if (currentEntry != null && currentEntry.name != null && currentEntry.name == tag) {
                ret = true
            }
        }
        return ret
    }

    class Option private constructor() {
        var isUseAnimation = true
            private set
        var isAddBackStack = true
            private set
        var isTransactionReplace = false
            private set
        var tag: String? = null

        class Builder {
            val option: Option

            init {
                this.option = Option()
            }

            fun setTag(tag: String): Builder {
                this.option.tag = tag
                return this
            }

            fun useAnimation(use: Boolean): Builder {
                this.option.isUseAnimation = use
                return this
            }

            fun addBackStack(addBackStack: Boolean): Builder {
                this.option.isAddBackStack = addBackStack
                return this
            }

            fun isTransactionReplace(isTransactionReplace: Boolean): Builder {
                this.option.isTransactionReplace = isTransactionReplace
                return this
            }
        }
    }
}