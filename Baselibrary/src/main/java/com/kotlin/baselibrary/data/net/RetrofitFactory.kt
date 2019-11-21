package com.kotlin.baselibrary.data.net

import com.kotlin.baselibrary.common.BaseConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * author：xman
 * created on: 2019/6/13 3:24 PM
 * description:
 *
 */
class RetrofitFactory private constructor() {

    companion object {
        val instance: RetrofitFactory by lazy { RetrofitFactory() }
    }

    private val retrofit:Retrofit
    private val interceptor :Interceptor

    init {
        interceptor = Interceptor {
            chain ->
            var request = Request.Builder()
                    .addHeader("charset","UTF-8")
                    .addHeader("Content_Type","application/json")
                    .build()
            chain.proceed(request)
        }

        retrofit = Retrofit.Builder()
                .baseUrl(BaseConstants.SERVER_ADDRESS)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(initOkHttpClint())
                .build()
    }

    /**
     * 创建httpclient
     */
    private fun initOkHttpClint(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(initLogInterceptor())
                .addInterceptor(interceptor)
                .build()
    }

    /**
     * 初始化interceptor
     */
    private fun initLogInterceptor(): Interceptor {
        var interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * 创建retrofit对象
     */
    fun <T> create(service:Class<T>):T{
        return retrofit.create(service)
    }
}