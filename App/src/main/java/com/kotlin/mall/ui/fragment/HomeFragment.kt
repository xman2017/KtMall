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
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *
 * author：xman
 * created on: 2019-11-25 21:23
 * description:
 *
 */
class HomeFragment:BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initWidget(mRoot: View?) {
        super.initWidget(mRoot)
        mRoot?.let { initBanner(it) }
        initNews()
    }

    private fun initNews() {
        //公告
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))
    }

    private fun initBanner(rootView: View) {
        //设置图片加载器
        mHomeBanner.setImageLoader(BannerImagerLoader())
        //设置图片集合
        mHomeBanner.setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE))
        //设置banner动画效果
        mHomeBanner.setBannerAnimation(Transformer.DepthPage)
        //设置自动轮播，默认为true
        mHomeBanner.isAutoPlay(true)
        //设置指示器位置（当banner模式中有指示器时）
        mHomeBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mHomeBanner.start()
    }


}