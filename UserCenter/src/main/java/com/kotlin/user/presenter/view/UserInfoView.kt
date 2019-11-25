package com.kotlin.user.presenter.view

import com.kotlin.baselibrary.presenter.view.BaseView
import com.kotlin.user.data.protocol.UserInfo

/**
 *
 * author：xman
 * created on: 2019/3/4 3:31 PM
 * description:
 *
 */
interface UserInfoView:BaseView {

    fun onGetUploadTokenResult(result:String)

    fun onEditUserResult(userInfo: UserInfo)

}