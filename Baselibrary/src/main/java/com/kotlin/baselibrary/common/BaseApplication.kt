package com.kotlin.baselibrary.common

import android.app.Application
import android.content.Context
import com.kotlin.baselibrary.injection.component.AppComponent
import com.kotlin.baselibrary.injection.component.DaggerAppComponent
import com.kotlin.baselibrary.injection.module.AppModule
import javax.inject.Inject

/**
 *
 * author：xman
 * created on: 2019/6/19 3:57 PM
 * description:
 *
 */
class BaseApplication:Application() {

    @Inject
    lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppInjection()
        context = this
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


    companion object {
        lateinit var context:Context
    }

}