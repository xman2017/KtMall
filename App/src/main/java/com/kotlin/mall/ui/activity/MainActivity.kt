package com.kotlin.mall.ui.activity

import com.kotlin.baselibrary.ui.activity.BaseActivity
import com.kotlin.mall.R
import com.kotlin.mall.ui.fragment.HomeFragment

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flContainer, HomeFragment())
        transaction.commit()
    }
}
