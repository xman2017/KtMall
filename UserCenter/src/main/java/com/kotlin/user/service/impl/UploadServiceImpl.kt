package com.kotlin.user.service.impl

import com.kotlin.baselibrary.ext.convert
import com.kotlin.user.data.repository.UploadRepository
import com.kotlin.user.service.UploadService
import io.reactivex.Observable
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019-11-24 01:04
 * description:
 *
 */
class UploadServiceImpl @Inject constructor():UploadService {

    @Inject
    lateinit var uploadRepository: UploadRepository

    override fun getUploadToken(): Observable<String> {
        return uploadRepository.getUploadToken().convert()
    }
}