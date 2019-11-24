package com.kotlin.user.injection.component

import com.kotlin.baselibrary.injection.PerScope
import com.kotlin.baselibrary.injection.component.ActivityComponent
import com.kotlin.user.injection.module.UploadModule
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.ui.activity.*
import dagger.Component

/**
 *
 * author：xman
 * created on: 2019/6/19 2:27 PM
 * description:
 *
 */
@Component(modules = [(UserModule::class),(UploadModule::class)],dependencies = [(ActivityComponent::class)])
@PerScope
interface UserComponent {

    fun inject(registerActivity: RegisterActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(forgetPwdActivity: ForgetPwdActivity)
    fun inject(resetPwdActivity: ResetPwdActivity)
    fun inject(userInfoActivity: UserInfoActivity)
}