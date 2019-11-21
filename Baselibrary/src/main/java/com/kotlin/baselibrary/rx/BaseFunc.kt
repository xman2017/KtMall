package com.kotlin.baselibrary.rx

import com.kotlin.baselibrary.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 *
 * author：xman
 * created on: 2019/6/20 10:06 AM
 * description:
 *
 */
class BaseFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        if (t.data == null){
            return Observable.error(DataNullException())
        }
        return Observable.just(t.data)
    }
}