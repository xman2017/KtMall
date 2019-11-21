package com.kotlin.baselibrary.injection.module

import android.app.Activity
import com.kotlin.baselibrary.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 *
 * author：xman
 * created on: 2019/6/19 4:10 PM
 * description:
 *
 */
@Module
class ActivityModule(private val activity:Activity) {

    @Provides
    @ActivityScope
    fun providesActivityModule():Activity{
        return activity
    }
}