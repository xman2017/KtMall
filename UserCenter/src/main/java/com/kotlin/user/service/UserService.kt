package com.kotlin.user.service

import com.kotlin.user.data.protocol.UserInfo
import io.reactivex.Observable


/**
 *
 * author：xman
 * created on: 2019/3/4 4:34 PM
 * description:
 *
 */
interface UserService {

    fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean>

    fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo>

    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>

    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>
}