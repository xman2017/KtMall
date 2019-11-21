package com.kotlin.baselibrary.injection.module

import com.trello.rxlifecycle2.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 *
 * author：xman
 * created on: 2019/6/19 5:09 PM
 * description:
 *
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @Provides
    fun providesLifecycleProvider():LifecycleProvider<*>{
        return this.lifecycleProvider
    }
}