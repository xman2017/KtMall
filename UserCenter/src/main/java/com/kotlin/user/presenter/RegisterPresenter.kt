package com.kotlin.user.presenter

import com.kotlin.baselibrary.ext.execute
import com.kotlin.baselibrary.presenter.BasePresenter
import com.kotlin.baselibrary.rx.BaserObserver
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.UserService
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/3/4 3:30 PM
 * description: 注册presenter
 *
 */
class RegisterPresenter @Inject constructor(): BasePresenter<RegisterView>() {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        if (!checkNetWorkAvailable()){
            return
        }
        mBaseView.showLoading("加载中")
        userService.register(mobile, pwd, verifyCode)
                .execute(object : BaserObserver<Boolean>(mBaseView) {
                    override fun onNext(t: Boolean) {
                        mBaseView.onRegisterResult(t)
                    }
                },lifecycleProvider)

    }

}