package com.kotlin.user.data.api

import com.kotlin.baselibrary.data.protocol.BaseResp
import com.kotlin.user.data.protocol.*
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 *
 * author：xman
 * created on: 2019/6/13 4:02 PM
 * description:
 *
 */
interface UserApi {

    @POST("userCenter/register")
    fun register(@Body registerReq: RegisterReq): Observable<BaseResp<String>>

    @POST("userCenter/login")
    fun login(@Body loginReq: LoginReq): Observable<BaseResp<UserInfo>>

    @POST("userCenter/forgetPwd")
    fun forgetPwd(@Body forgetPwdReq: ForgetPwdReq): Observable<BaseResp<String>>

    @POST("userCenter/resetPwd")
    fun resetPwd(@Body resetPwdReq: ResetPwdReq): Observable<BaseResp<String>>
}