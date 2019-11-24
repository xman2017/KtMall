package com.kotlin.user.service

import io.reactivex.Observable


/**
 *
 * author：xman
 * created on: 2019/3/4 4:34 PM
 * description:
 *
 */
interface UploadService {

    fun getUploadToken(): Observable<String>
}