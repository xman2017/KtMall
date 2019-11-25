package com.kotlin.user.data.repository

import com.kotlin.baselibrary.data.net.RetrofitFactory
import com.kotlin.baselibrary.data.protocol.BaseResp
import com.kotlin.user.data.api.UserApi
import com.kotlin.user.data.protocol.*
import io.reactivex.Observable
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/6/13 4:08 PM
 * description:
 *
 */
class UserRepository @Inject constructor(){

    /**
     * 注册  实际请求数据的部分
     */
    fun register(mobile:String,pwd:String,verifyCode:String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).register(RegisterReq(mobile,pwd,verifyCode))
    }

    /**
     * 登陆  实际请求数据的部分
     */
    fun login(mobile:String,pwd:String,pushId:String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).login(LoginReq(mobile,pwd,pushId))
    }

    /**
     * 忘记密码  实际请求数据的部分
     */
    fun forgetPwd(mobile:String,verifyCode:String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).forgetPwd(ForgetPwdReq(mobile,verifyCode))
    }

    /**
     * 重置密码  实际请求数据的部分
     */
    fun resetPwd(mobile:String,pwd:String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UserApi::class.java).resetPwd(ResetPwdReq(mobile,pwd))
    }

    /**
     * 编辑用户信息
     */
    fun editUser(userIcon: String, userName: String, gender: String, sign: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.create(UserApi::class.java).editUser(EditUserReq(userIcon,userName,gender,sign))
    }
}