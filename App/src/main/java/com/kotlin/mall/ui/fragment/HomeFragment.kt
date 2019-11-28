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
class HomeFragment : BaseFragment() {


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initWidget(mRoot: View?) {
        super.initWidget(mRoot)
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }

    private fun initTopic() {

        mTopicPager.adapter = TopicAdapter(act, listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE))
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder()
                .with(mTopicPager)
                .pagerMargin(0f)
                .scale(0.3f)
                .spaceSize(0f)
                .rotationY(0f)
                .build()

        mTopicContainer.setPageItemClickListener(PageItemClickListener { view, position ->
//            toast("position:" + position)
        })
    }

    private fun initDiscount() {
        val rvManager = LinearLayoutManager(context)
        rvManager.orientation = LinearLayoutManager.HORIZONTAL
        mHomeDiscountRv.layoutManager = rvManager

        val discountAdapter = HomeDiscountAdapter<String, HomeDiscountAdapter.HomeDiscountViewHolder>(act)
        discountAdapter.setData(mutableListOf(HOME_DISCOUNT_ONE, HOME_DISCOUNT_TWO, HOME_DISCOUNT_THREE, HOME_DISCOUNT_FOUR, HOME_DISCOUNT_FIVE))
        mHomeDiscountRv.adapter = discountAdapter
    }

    private fun initNews() {
        //公告
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))
    }

    private fun initBanner() {
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