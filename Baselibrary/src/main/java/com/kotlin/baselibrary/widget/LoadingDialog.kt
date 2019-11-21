package com.kotlin.baselibrary.widget

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.kotlin.baselibrary.R
import kotlinx.android.synthetic.main.progress_dialog.*
import org.jetbrains.anko.find

/*
    加载对话框封装
 */
class LoadingDialog private constructor(context: Context, theme: Int) : Dialog(context, theme) {

    companion object {
        private lateinit var mDialog: LoadingDialog
        private var animDrawable: AnimationDrawable? = null
        private lateinit var msgTV:TextView

        /*
            创建加载对话框
         */
        fun create(context: Context):LoadingDialog {
            //样式引入
            mDialog = LoadingDialog(context, R.style.LightProgressDialog)
            //设置布局
            mDialog.setContentView(R.layout.progress_dialog)
            mDialog.setCancelable(true)
            mDialog.setCanceledOnTouchOutside(false)
            mDialog.window.attributes.gravity = Gravity.CENTER

            val lp = mDialog.window.attributes
            lp.dimAmount = 0.2f
            //设置属性
            mDialog.window.attributes = lp

            //获取动画视图
            val loadingView = mDialog.find<ImageView>(R.id.iv_loading)
            animDrawable = loadingView.background as AnimationDrawable

            return mDialog
        }
    }

    /*
        显示加载对话框，动画开始
     */
    fun showLoading() {
        super.show()
        animDrawable?.start()
    }

    /*
        隐藏加载对话框，动画停止
     */
    fun hideLoading(){
        super.dismiss()
        animDrawable?.stop()
    }

    fun setMessage(msg:String){
        tv_message.setText(msg)
    }
}
