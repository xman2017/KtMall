package com.kotlin.user.presenter

import com.kotlin.baselibrary.ext.execute
import com.kotlin.baselibrary.presenter.BasePresenter
import com.kotlin.baselibrary.rx.BaserObserver
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.presenter.view.ForgetPwdView
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
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetWorkAvailable()) {
            return
        }
        mBaseView.showLoading("加载中")
        userService.forgetPwd(mobile, verifyCode)
                .execute(object : BaserObserver<Boolean>(mBaseView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mBaseView.onForgetPwdResult("验证成功")
                    }
                }, lifecycleProvider)

    }

}