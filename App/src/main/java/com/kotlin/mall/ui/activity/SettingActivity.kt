package com.kotlin.mall.ui.activity

import com.kotlin.baselibrary.ext.onClick
import com.kotlin.baselibrary.ui.activity.BaseActivity
import com.kotlin.mall.R
import com.kotlin.user.ui.activity.LoginActivity
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.intentFor

/**
 *
 * @Author:         xman
 * @CreateDate:     2019/11/28 15:10
 * @Description:
 *
 */
class SettingActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        super.initData()
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            startActivity(intentFor<LoginActivity>())
        }
    }
}