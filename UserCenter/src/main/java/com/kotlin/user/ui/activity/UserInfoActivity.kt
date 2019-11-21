package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.kotlin.baselibrary.ext.enable
import com.kotlin.baselibrary.ext.onClick
import com.kotlin.baselibrary.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ResetPwdPresenter
import com.kotlin.user.presenter.UserInfoPresenter
import com.kotlin.user.presenter.view.ResetPwdView
import com.kotlin.user.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener {

    override fun onResetPwdResult(result: String) {

    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mBaseView = this
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        initViews()
    }

    private fun initViews() {
        mUserIconIv.onClick { showAlertView() }
    }

    private fun showAlertView() {
        AlertView("选择头像", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                when (position) {
                    0 -> toast("拍照")
                    1 -> toast("相册")
                }
            }
        }).show()

    }

    override fun onClick(v: View) {

    }


}
