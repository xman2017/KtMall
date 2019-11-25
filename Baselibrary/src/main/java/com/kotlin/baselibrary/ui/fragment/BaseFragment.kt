package com.kotlin.baselibrary.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment

open class BaseFragment : RxFragment() {

    var mRoot: View? = null

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        initMvpComponent()
    }

    open fun initMvpComponent() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            initBundle(arguments!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if (mRoot != null) {
            val parent = mRoot?.getParent() as ViewGroup
            parent?.removeView(mRoot)
        } else {
            mRoot = inflater.inflate(getLayoutId(), container, false)
            // Do something
            onBindViewBefore(mRoot)
            // Get savedInstanceState
            onRestartInstance(savedInstanceState)
            // Init
            initWidget(mRoot)
            initData()
        }
        return mRoot
    }

    open fun initBundle(bundle: Bundle) {

    }

    open fun initWidget(mRoot: View?) {

    }

    open fun initData() {

    }

    open fun onRestartInstance(savedInstanceState: Bundle?) {

    }

    open fun onBindViewBefore(mRoot: View?) {

    }

    open fun getLayoutId(): Int {
        return -1
    }
}
