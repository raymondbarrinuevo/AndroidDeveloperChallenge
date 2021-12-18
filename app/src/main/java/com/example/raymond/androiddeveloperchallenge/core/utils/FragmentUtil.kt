package com.example.raymond.androiddeveloperchallenge.core.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.raymond.androiddeveloperchallenge.R
import com.example.raymond.androiddeveloperchallenge.core.view.BaseAppActivity
import com.example.raymond.androiddeveloperchallenge.core.view.BaseFragment

class FragmentUtil {

    private val TAG = FragmentUtil::class.java.simpleName

    /**
     * goto Fragment by class
     *
     * @param context
     * @param cls     class of fragment
     */
    fun gotoFragment(context: Context, cls: Class<*>) {
        gotoFragment(context, cls, false)
    }

    /**
     * goto Fragment by class
     *
     * @param context
     * @param cls         class of fragment
     * @param isRefreshUI
     */
    fun gotoFragment(context: Context?, cls: Class<*>, isRefreshUI: Boolean) {
        if (context == null) {
            return
        }

        try {
            val fragmentManager = (context as FragmentActivity)
                    .supportFragmentManager
            if (fragmentManager.backStackEntryCount >= 0) {
                for (i in fragmentManager.backStackEntryCount - 1 downTo 0) {
                    if (fragmentManager.getBackStackEntryAt(i).name != null && fragmentManager.getBackStackEntryAt(i).name == cls
                                    .simpleName) {
                        if (isRefreshUI) {
                            onResumeFragment(context, cls)
                        }
                        return
                    } else {
                        removeFragmentNoAnimation(context, getCurrentFragment(context))
                    }
                }
            }

        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }

    }

    /**
     * goto Fragment by tag
     *
     * @param context
     * @param tag
     */
    fun gotoFragment(context: Context?, tag: String) {
        if (context == null) {
            return
        }
        try {
            val fragmentManager = (context as FragmentActivity)
                    .supportFragmentManager
            if (fragmentManager.backStackEntryCount >= 0) {
                for (i in fragmentManager.backStackEntryCount - 1 downTo 0) {
                    if (fragmentManager.getBackStackEntryAt(i).name != null && fragmentManager.getBackStackEntryAt(i).name == tag) {

                        if (fragmentManager.findFragmentByTag(tag) != null) {
                            onResumeFragment(context, fragmentManager.findFragmentByTag(tag)!!.javaClass)
                        }
                        return
                    } else {
                        removeFragmentNoAnimation(context, getCurrentFragment(context))
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * Check fragment is created
     *
     * @param context
     * @param cls     class is fragment need check
     * @return status after check
     */
    fun hasFragment(context: Context?, cls: Class<*>): Boolean {
        if (context == null) {
            return false
        }
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        if (fragmentManager.backStackEntryCount >= 0) {
            for (i in fragmentManager.backStackEntryCount - 1 downTo 0) {
                if (fragmentManager.getBackStackEntryAt(i).name != null && fragmentManager
                                .getBackStackEntryAt(i).name == cls.simpleName) {
                    return true
                }
            }
        }

        return false
    }

    /**
     * check fragment created, it don't have last fragment
     *
     * @param context
     * @param cls     class is fragment need check
     * @return status after check
     */
    fun hasPreviousFragment(context: Context?, cls: Class<*>): Boolean {
        if (context == null) {
            return false
        }
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        if (fragmentManager.backStackEntryCount >= 0) {
            for (i in fragmentManager.backStackEntryCount - 2 downTo 0) {
                if (fragmentManager.getBackStackEntryAt(i).name != null && fragmentManager
                                .getBackStackEntryAt(i).name == cls.simpleName) {
                    return true
                }
            }
        }

        return false
    }

    private fun onResumeFragment(context: Context, cls: Class<*>?) {
        if (cls == null) {
            return
        }
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        val visibleFragment = fragmentManager.findFragmentByTag(cls.simpleName)
        if (visibleFragment != null && visibleFragment is BaseFragment) {
            (visibleFragment as BaseFragment).onResumeFragment()
        }
    }

    /**
     * remove fragment no animation
     *
     * @param context
     */
    private fun removeFragmentNoAnimation(context: Context?, fragment: Fragment?) {
        if (context == null) {
            return
        }

        try {
            val fragmentManager = (context as FragmentActivity)
                    .supportFragmentManager
            fragmentManager.popBackStack()
            fragment?.let { fragmentManager.beginTransaction().remove(it).commitAllowingStateLoss() }
        } catch (e: Exception) {
            //FileLogger.e(TAG, "Error remove fragment no animation: " + e.toString());
        }

    }

    /**
     *
     * find fragment
     *
     * @param context
     */
    fun getCurrentFragment(context: Context?): Fragment? {
        if (context == null) {
            return null
        }
        val fragmentManager = (context as FragmentActivity).supportFragmentManager

        var fragment: Fragment? = null
        val count = fragmentManager.backStackEntryCount
        if (count > 0) {
            fragment = fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(count - 1).name)
        }

        if (fragment == null) {
            fragment = fragmentManager.findFragmentById(R.id.mainContainer)
        }

        if (fragment == null) {
            fragment = fragmentManager.findFragmentById(R.id.child_fragment_container)
        }

        return fragment
    }

    /**
     * find fragment
     *
     * @param context
     */
    fun getPreviousFragment(context: Context?): Fragment? {
        if (context == null) {
            return null
        }
        val fragmentManager = (context as FragmentActivity).supportFragmentManager

        val count = fragmentManager.backStackEntryCount

        return if (count > 1) {
            fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(count - 2).name)
        } else null

    }

    /**
     * find fragment
     *
     * @param context
     * @param cls     class is fragment need check
     */
    fun findFragment(context: Context?, cls: Class<*>): Fragment? {
        if (context == null) {
            return null
        }
        val fragmentManager = (context as FragmentActivity).supportFragmentManager

        return fragmentManager.findFragmentByTag(cls.simpleName)
    }

    /**
     * check fragment is active
     *
     * @param context
     * @param cls
     * @return
     */
    fun isActiveFragment(context: Context?, cls: Class<*>): Boolean {
        if (context == null) {
            return false
        }
        val fragmentManager = (context as FragmentActivity).supportFragmentManager
        val count = fragmentManager.backStackEntryCount

        if (count <= 0) {
            return false
        }
        return if (fragmentManager.getBackStackEntryAt(count - 1) != null && fragmentManager
                        .getBackStackEntryAt(count - 1).name != null && fragmentManager
                        .getBackStackEntryAt(count - 1).name == cls.simpleName) {
            true
        } else false

    }

    /**
     * clear all back stack
     *
     * @param context
     */
    fun clearAllBackStack(context: Context?) {
        if (context == null) {
            return
        }

        try {
            val fragmentManager = (context as FragmentActivity)
                    .supportFragmentManager
            if (fragmentManager.backStackEntryCount >= 0) {
                for (i in fragmentManager.backStackEntryCount - 1 downTo 0) {
                    removeFragmentNoAnimation(context, getCurrentFragment(context))
                }
            }

            hideKeyboard(context)

        } catch (e: Exception) {
            //FileLogger.e(TAG, "Error clear all back stack: " + e.toString());
        }

    }

    /**
     * replace fragment (screen)
     *
     * @param fragment
     * @param tag
     */
    fun replaceFragment(context: Context?, fragment: Fragment?, tag: String) {
        if (context == null || fragment == null) {
            return
        }

        try {
            val currentFragment = getCurrentFragment(context)
            if (currentFragment != null && currentFragment!!.javaClass.getSimpleName() == fragment.javaClass.simpleName) {
                return
            }
            if (context as FragmentActivity? is BaseAppActivity) {
                context.supportFragmentManager.beginTransaction()
                        .replace(R.id.mainContainer, fragment, tag).commitAllowingStateLoss()
            }

            hideKeyboard(context)

        } catch (e: Exception) {
            //FileLogger.e(TAG, "Error replace fragment: " + e.toString());
        }

    }

    /**
     * Start new fragment (screen)
     *
     * @param context
     * @param fragment
     * @param tag
     */
    fun startFragment(context: Context?, fragment: Fragment?, tag: String) {
        if (context == null || fragment == null) {
            return
        }
        startFragment(context, fragment, R.id.mainContainer, tag)
    }

    /**
     * Start new fragment (screen)
     *
     * @param context
     * @param fragment
     * @param layoutId
     * @param tag
     */
    fun startFragment(context: Context?, fragment: Fragment?, layoutId: Int, tag: String?) {
        var tag = tag
        if (context == null || fragment == null) {
            return
        }


        try {
            if (tag == null) {
                tag = fragment.javaClass.simpleName
            }
            val currentFragment = getCurrentFragment(context)
            if (currentFragment != null && currentFragment!!.javaClass.getSimpleName() == fragment.javaClass.simpleName) {
                return
            }

            hideKeyboard(context)
        } catch (e: Exception) {
            //FileLogger.e(TAG, "Error start fragment: " + e.toString());
        }

    }

    /**
     * Hidden Keyboard
     *
     * @param context
     */
    fun showKeyboard(context: Context?) {
        if (context == null) {
            return
        }
        val imm = context.getSystemService(FragmentActivity
                .INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    /**
     * Hidden Keyboard
     *
     * @param context
     */
    fun hideKeyboard(context: Context?) {
        if (context == null) {
            return
        }
        // Check if no view has focus:
        val view = (context as FragmentActivity).currentFocus
        if (view != null) {
            val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager
                    .RESULT_UNCHANGED_SHOWN)
        }
    }
}