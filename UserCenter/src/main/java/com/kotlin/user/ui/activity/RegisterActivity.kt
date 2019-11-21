package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.baselibrary.common.AppManager
import com.kotlin.baselibrary.ext.enable
import com.kotlin.baselibrary.ext.onClick
import com.kotlin.baselibrary.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    var pressTime: Long = 0

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mBaseView = this
    }

    override fun onRegisterResult(succeed: Boolean) {
        if (succeed) {
            toast("注册成功！")
        } else {
            toast("注册失败")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.enable(mMobileEt) { isBtnEnable() }
        mRegisterBtn.enable(mVerifyCodeEt) { isBtnEnable() }
        mRegisterBtn.enable(mPwdEt) { isBtnEnable() }
        mRegisterBtn.enable(mPwdConfirmEt) { isBtnEnable() }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mRegisterBtn -> {
                mBasePresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
            }
            R.id.mVerifyCodeBtn -> {
                toast("发送验证码成功")
                mVerifyCodeBtn.requestSendVerifyNumber()
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.toString().isNotEmpty() &&
                mVerifyCodeEt.text.toString().isNotEmpty() &&
                mPwdEt.text.toString().isNotEmpty() &&
                mPwdConfirmEt.text.toString().isNotEmpty()
    }


    override fun onBackPressed() {
        var curMillis = System.currentTimeMillis()
        if (curMillis - pressTime > 2000) {
            pressTime = curMillis
            toast("再按一次退出应用！")
        } else {
            AppManager.instance.exitApp(this)
        }

    }

}
