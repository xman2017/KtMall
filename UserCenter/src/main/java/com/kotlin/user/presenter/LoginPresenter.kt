package com.kotlin.user.presenter

import com.kotlin.baselibrary.ext.execute
import com.kotlin.baselibrary.presenter.BasePresenter
import com.kotlin.baselibrary.rx.BaserObserver
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.presenter.view.LoginView
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
class LoginPresenter @Inject constructor(): BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserService

    fun login(mobile: String, pwd: String, verifyCode: String) {
        if (!checkNetWorkAvailable()){
            return
        }
        mBaseView.showLoading("加载中")
        userService.login(mobile, pwd, verifyCode)
                .execute(object : BaserObserver<UserInfo>(mBaseView) {
                    override fun onNext(t: UserInfo) {
                        mBaseView.onLoginResult(t)
                    }
                },lifecycleProvider)

    }

}