package com.kotlin.baselibrary.injection.component

import android.app.Activity
import android.content.Context
import com.kotlin.baselibrary.injection.ActivityScope
import com.kotlin.baselibrary.injection.module.ActivityModule
import com.kotlin.baselibrary.injection.module.LifecycleProviderModule
import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Component

/**
 *
 * author：xman
 * created on: 2019/6/19 4:14 PM
 * description:
 *
 */
@Component(modules = [(ActivityModule::class), (LifecycleProviderModule::class)],dependencies = [(AppComponent::class)])
@ActivityScope
interface ActivityComponent {

    fun activity(): Activity
    fun context(): Context
    fun lifecycleProvider(): LifecycleProvider<*>
}