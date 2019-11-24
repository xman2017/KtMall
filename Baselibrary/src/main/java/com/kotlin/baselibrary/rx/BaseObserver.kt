package com.kotlin.baselibrary.rx

import com.kotlin.baselibrary.presenter.view.BaseView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 *
 * author：xman
 * created on: 2019/3/4 4:49 PM
 * description:
 *
 */
open class BaseObserver<T>(var mView: BaseView): Observer<T> {
    override fun onComplete() {
        mView.hideLoading()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        mView.hideLoading()
        if (e is BaseException){
            mView.onError(e.msg)
        }else if (e is DataNullException){
            mView.onError("数据为空")
        }
    }

}