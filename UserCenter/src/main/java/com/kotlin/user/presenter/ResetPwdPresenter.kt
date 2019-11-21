package com.kotlin.user.presenter

import com.kotlin.baselibrary.ext.execute
import com.kotlin.baselibrary.presenter.BasePresenter
import com.kotlin.baselibrary.rx.BaserObserver
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.presenter.view.LoginView
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.presenter.view.ResetPwdView
import com.kotlin.user.service.UserService
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/3/4 3:30 PM
 * description: 重置密码presenter
 *
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService: UserService

    fun resetPwd(mobile: String, pwd: String) {
        if (!checkNetWorkAvailable()) {
            return
        }
        mBaseView.showLoading("加载中")
        userService.resetPwd(mobile, pwd)
                .execute(object : BaserObserver<Boolean>(mBaseView) {
                    override fun onNext(t: Boolean) {
                        if (t)
                            mBaseView.onResetPwdResult("重置密码成功")
                    }
                }, lifecycleProvider)

    }

}