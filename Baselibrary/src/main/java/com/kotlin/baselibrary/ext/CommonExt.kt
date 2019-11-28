package com.kotlin.baselibrary.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.kotlin.baselibrary.data.protocol.BaseResp
import com.kotlin.baselibrary.rx.BaseFunc
import com.kotlin.baselibrary.rx.BaseFuncBoolean
import com.kotlin.baselibrary.rx.BaseObserver
import com.kotlin.baselibrary.widget.DefaultTextWatcher
import com.trello.rxlifecycle2.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *
 * author：xman
 * created on: 2019/3/4 4:55 PM
 * description:
 *
 */

fun <T> Observable<T>.execute(observer: BaseObserver<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(observer)
}

fun <T> Observable<BaseResp<T>>.convert(): Observable<T> {
    return this.flatMap(BaseFunc<T>())
}

fun <T> Observable<BaseResp<T>>.convertBoolean(): Observable<Boolean> {
    return this.flatMap(BaseFuncBoolean<T>())
}

fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

fun View.onClick(method: () -> Unit): View {
    setOnClickListener { method() }
    return this
}

fun Button.enable(et: EditText, method: () -> Boolean) {
    var btn = this
    et.addTextChangedListener(object : DefaultTextWatcher() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}

fun ImageView.loadUrl(url:String){
    GlideUtils.loadUrlImage(context,url,this)
}