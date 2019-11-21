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
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/3/4 3:34 PM
 * description:
 *
 */
abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    override fun showLoading(loadMsg: String) {
    }

    override fun hideLoading() {
    }

    override fun onError(errMsg: String) {
        toast(errMsg)
    }

    @Inject
    lateinit var mBasePresenter: T

    @Inject
    lateinit var activityComponent:ActivityComponent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        initActivityComponent()
        injectComponent()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun injectComponent()

    private fun initActivityComponent() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent((act.application as BaseApplication).appComponent)
                .activityModule(ActivityModule(act))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }
}