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
class BaseFuncBoolean<T> : Function<BaseResp<T>, Observable<Boolean>> {
    override fun apply(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }
        return Observable.just(true)
    }
}