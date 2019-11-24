package com.kotlin.user.presenter

import com.kotlin.baselibrary.ext.execute
import com.kotlin.baselibrary.presenter.BasePresenter
import com.kotlin.baselibrary.rx.BaseObserver
import com.kotlin.user.presenter.view.UserInfoView
import com.kotlin.user.service.UploadService
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

    @Inject
    lateinit var uploadService: UploadService

    fun getUploadToken(){
        if (!checkNetWorkAvailable()){
            return
        }
        mBaseView.showLoading("正在获取图片token")
        uploadService.getUploadToken().execute(object :BaseObserver<String>(mBaseView){
            override fun onNext(t: String) {
                super.onNext(t)
                mBaseView.onGetUploadTokenResult(t)
            }
        },lifecycleProvider)
    }

}