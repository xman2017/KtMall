package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.baselibrary.ext.enable
import com.kotlin.baselibrary.ext.onClick
import com.kotlin.baselibrary.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ForgetPwdPresenter
import com.kotlin.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mBaseView = this
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<ResetPwdActivity>("mobile" to mMobileEt.text.toString()))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_forget_pwd
    }

    override fun initView() {
        super.initView()

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
        mNextBtn.enable(mMobileEt) { isBtnEnable() }
        mNextBtn.enable(mVerifyCodeEt) { isBtnEnable() }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mNextBtn -> {
                mBasePresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
            }
            R.id.mVerifyCodeBtn -> {
                toast("发送验证码成功")
                mVerifyCodeBtn.requestSendVerifyNumber()
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.toString().isNotEmpty() &&
                mVerifyCodeEt.text.toString().isNotEmpty()
    }


}
