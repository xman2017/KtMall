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
interface UploadApi {

    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>

}