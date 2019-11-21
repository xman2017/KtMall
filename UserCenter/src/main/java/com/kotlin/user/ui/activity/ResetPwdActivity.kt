package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.baselibrary.ext.enable
import com.kotlin.baselibrary.ext.onClick
import com.kotlin.baselibrary.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ResetPwdPresenter
import com.kotlin.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.toast

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView, View.OnClickListener {


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mBaseView = this
    }

    override fun onResetPwdResult(result: String) {
        toast(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        mConfirmBtn.onClick(this)
        mConfirmBtn.enable(mPwdEt) { isBtnEnable() }
        mConfirmBtn.enable(mPwdConfirmEt) { isBtnEnable() }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mConfirmBtn -> {
                mBasePresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mPwdConfirmEt.text.toString().isNotEmpty() &&
                mPwdEt.text.toString().isNotEmpty()
    }


}
