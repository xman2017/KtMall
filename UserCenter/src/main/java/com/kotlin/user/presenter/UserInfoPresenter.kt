package com.kotlin.user.presenter

import com.kotlin.baselibrary.ext.execute
import com.kotlin.baselibrary.presenter.BasePresenter
import com.kotlin.baselibrary.rx.BaserObserver
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.presenter.view.LoginView
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.presenter.view.ResetPwdView
import com.kotlin.user.presenter.view.UserInfoView
import com.kotlin.user.service.UserService
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/3/4 3:30 PM
 * description: 重置密码presenter
 *
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var userService: UserService

}