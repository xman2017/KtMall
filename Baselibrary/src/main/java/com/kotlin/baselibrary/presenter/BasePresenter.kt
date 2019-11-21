package com.kotlin.baselibrary.presenter

import android.content.Context
import com.kotlin.base.utils.NetWorkUtils
import com.kotlin.baselibrary.presenter.view.BaseView
import com.trello.rxlifecycle2.LifecycleProvider
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/3/4 2:53 PM
 * description:
 *
 */
open class BasePresenter<T : BaseView> {

    lateinit var mBaseView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context

    /**
     * 检查网络状态
     */
    fun checkNetWorkAvailable(): Boolean {
        if (!NetWorkUtils.isNetWorkAvailable(context)) {
           mBaseView.onError("网络不可用")
            return false
        }
        return true
    }
}