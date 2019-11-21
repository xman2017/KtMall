package com.kotlin.user.injection.module

import com.kotlin.baselibrary.injection.PerScope
import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 *
 * author：xman
 * created on: 2019/6/19 2:18 PM
 * description:
 *
 */
@Module
class UserModule {

    @Provides
    @PerScope
    fun providesUserService(service: UserServiceImpl):UserService{
        return service
    }
}