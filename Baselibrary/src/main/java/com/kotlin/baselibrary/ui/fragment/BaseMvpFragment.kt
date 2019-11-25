package com.kotlin.baselibrary.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.baselibrary.common.BaseApplication
import com.kotlin.baselibrary.injection.component.ActivityComponent
import com.kotlin.baselibrary.injection.component.DaggerActivityComponent
import com.kotlin.baselibrary.injection.module.ActivityModule
import com.kotlin.baselibrary.injection.module.LifecycleProviderModule
import com.kotlin.baselibrary.presenter.BasePresenter
import com.kotlin.baselibrary.presenter.view.BaseView
import com.kotlin.baselibrary.widget.LoadingDialog
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/3/4 3:34 PM
 * description:
 *
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    @Inject
    lateinit var mBasePresenter: T

    @Inject
    lateinit var activityComponent: ActivityComponent

    lateinit var mLoadingDialog: LoadingDialog


    override fun initMvpComponent() {
        initActivityComponent()
        injectComponent()
        mLoadingDialog = LoadingDialog.create(act)

    }

    abstract fun injectComponent()

    private fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((act.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(act))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }


    override fun showLoading(loadMsg: String) {
        if (mLoadingDialog != null) {
            mLoadingDialog.setMessage(loadMsg)
            mLoadingDialog.showLoading()
        }
    }

    override fun hideLoading() {
        if (mLoadingDialog != null)
            mLoadingDialog.hideLoading()
    }

    override fun onError(errMsg: String) {
        toast(errMsg)
    }

}