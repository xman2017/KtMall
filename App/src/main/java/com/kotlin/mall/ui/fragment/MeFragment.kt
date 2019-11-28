package com.kotlin.mall.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kotlin.baselibrary.common.BaseConstants
import com.kotlin.baselibrary.ext.loadUrl
import com.kotlin.baselibrary.ext.onClick
import com.kotlin.baselibrary.ui.fragment.BaseFragment
import com.kotlin.baselibrary.utils.MmkvUtils
import com.kotlin.baselibrary.widget.BannerImagerLoader
import com.kotlin.mall.R
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.activity.SettingActivity
import com.kotlin.mall.ui.adapter.HomeDiscountAdapter
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.kotlin.provider.common.ProviderConstants
import com.kotlin.provider.common.isLogined
import com.kotlin.user.ui.activity.LoginActivity
import com.kotlin.user.ui.activity.UserInfoActivity
import com.kotlin.user.utils.UserPrefsUtils
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.act
import me.crosswall.lib.coverflow.CoverFlow
import me.crosswall.lib.coverflow.core.PageItemClickListener
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


/**
 *
 * author：xman
 * created on: 2019-11-25 21:23
 * description:
 *
 */
class MeFragment : BaseFragment(), View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

    override fun initData() {
        super.initData()
        initListener()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun initListener() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mSettingTv.onClick(this)
    }

    private fun loadData() {
        if (isLogined()) {
            mUserNameTv.text = MmkvUtils.getString(ProviderConstants.KEY_SP_USER_NAME)
            mUserIconIv.loadUrl(MmkvUtils.getString(ProviderConstants.KEY_SP_USER_ICON))
        } else {
            mUserNameTv.text = getString(R.string.un_login_text)
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if (isLogined()) {
                    startActivity(intentFor<UserInfoActivity>())
                } else {
                    startActivity(intentFor<LoginActivity>())
                }
            }
            R.id.mSettingTv ->
                startActivity(intentFor<SettingActivity>())
        }
    }

}