package com.kotlin.baselibrary.ui.activity

import android.os.Bundle
import com.kotlin.baselibrary.common.AppManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

open class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutId() != -1)
            setContentView(getLayoutId())
        initMvpComponent()
        initBundle(savedInstanceState)
        initView()
        initData()
        AppManager.instance.addActivity(this)
    }

    open fun initMvpComponent() {

    }

    open fun initData() {

    }

    open fun initView() {

    }

    open fun initBundle(savedInstanceState: Bundle?) {

    }

    open fun getLayoutId(): Int {
        return -1
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

}
