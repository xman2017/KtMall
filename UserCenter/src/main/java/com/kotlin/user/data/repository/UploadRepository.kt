package com.kotlin.user.data.repository

import com.kotlin.baselibrary.data.net.RetrofitFactory
import com.kotlin.baselibrary.data.protocol.BaseResp
import com.kotlin.user.data.api.UploadApi
import io.reactivex.Observable
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019-11-24 01:07
 * description:
 *
 */
class UploadRepository @Inject constructor() {

    /**
     * 注册  实际请求数据的部分
     */
    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }


}