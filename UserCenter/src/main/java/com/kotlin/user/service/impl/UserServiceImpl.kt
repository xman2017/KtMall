package com.kotlin.user.service.impl

import com.kotlin.baselibrary.ext.convert
import com.kotlin.baselibrary.ext.convertBoolean
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/3/4 4:36 PM
 * description:
 *
 */
class UserServiceImpl @Inject constructor():UserService {

    @Inject
    lateinit var userRepository:UserRepository

    override fun register(mobile: String, pwd: String, verifyCode: String): Observable<Boolean> {
        return userRepository.register(mobile,pwd,verifyCode).convertBoolean()
    }

    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        return userRepository.login(mobile,pwd,pushId).convert()
    }

    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        return userRepository.forgetPwd(mobile,verifyCode).convertBoolean()
    }

    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        return userRepository.resetPwd(mobile,pwd).convertBoolean()
    }
}
