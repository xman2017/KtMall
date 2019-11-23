package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.baselibrary.ext.enable
import com.kotlin.baselibrary.ext.onClick
import com.kotlin.baselibrary.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.LoginPresenter
import com.kotlin.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mBaseView = this
    }

    override fun onLoginResult(result: UserInfo) {
        toast("登陆成功")
        startActivity<UserInfoActivity>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mLoginBtn.onClick(this)
        mForgetPwdTv.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mLoginBtn.enable(mMobileEt) { isBtnEnable() }
        mLoginBtn.enable(mPwdEt) { isBtnEnable() }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mLoginBtn -> {
                mBasePresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")
            }
            R.id.mRightTv -> {
                startActivity(intentFor<RegisterActivity>())
            }
            R.id.mForgetPwdTv -> {
                startActivity(intentFor<ForgetPwdActivity>())
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.toString().isNotEmpty() &&
                mPwdEt.text.toString().isNotEmpty()
    }


}
