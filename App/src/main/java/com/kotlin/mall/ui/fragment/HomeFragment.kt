package com.kotlin.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.baselibrary.ui.fragment.BaseFragment
import com.kotlin.baselibrary.widget.BannerImagerLoader
import com.kotlin.mall.R
import com.kotlin.mall.common.HOME_BANNER_ONE
import com.kotlin.mall.common.HOME_BANNER_THREE
import com.kotlin.mall.common.HOME_BANNER_TWO
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer

/**
 *
 * author：xman
 * created on: 2019-11-25 21:23
 * description:
 *
 */
class HomeFragment:BaseFragment() {

    lateinit var mBanner:Banner

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initWidget(mRoot: View?) {
        super.initWidget(mRoot)
        mRoot?.let { initBanner(it) }
    }

    private fun initBanner(rootView: View) {
        mBanner = rootView.findViewById(R.id.mHomeBanner)
        //设置图片加载器
        mBanner.setImageLoader(BannerImagerLoader())
        //设置图片集合
        mBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE))
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage)
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true)
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start()
    }


}