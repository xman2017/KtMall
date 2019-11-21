package com.kotlin.baselibrary.injection.component

import android.content.Context
import com.kotlin.baselibrary.injection.module.AppModule
import dagger.Component

import javax.inject.Singleton
/**
 *
 * author：xman
 * created on: 2019/6/19 4:02 PM
 * description:
 *
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun context():Context
}
