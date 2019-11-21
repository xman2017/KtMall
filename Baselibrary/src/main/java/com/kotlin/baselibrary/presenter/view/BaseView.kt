package com.kotlin.baselibrary.presenter.view

/**
 *
 * author：xman
 * created on: 2019/3/4 2:25 PM
 * description:
 *
 */
interface BaseView {
    fun showLoading(loadMsg:String)
    fun hideLoading()
    fun onError(errMsg:String)
}