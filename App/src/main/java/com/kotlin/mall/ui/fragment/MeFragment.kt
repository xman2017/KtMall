package com.kotlin.mall.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kotlin.baselibrary.ui.fragment.BaseFragment
import com.kotlin.baselibrary.widget.BannerImagerLoader
import com.kotlin.mall.R
import com.kotlin.mall.common.*
import com.kotlin.mall.ui.adapter.HomeDiscountAdapter
import com.kotlin.mall.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.act
import me.crosswall.lib.coverflow.CoverFlow
import me.crosswall.lib.coverflow.core.PageItemClickListener
import org.jetbrains.anko.support.v4.toast


/**
 *
 * author：xman
 * created on: 2019-11-25 21:23
 * description:
 *
 */
class MeFragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_me
    }

}