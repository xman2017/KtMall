package com.kotlin.mall.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.utils.GlideUtils
import com.kotlin.mall.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 *
 * @Author:         xman
 * @CreateDate:     2019/11/28 13:34
 * @Description:
 *
 */
class TopicAdapter(private val context: Context, private val imageList: List<String>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return imageList?.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, paramterObject: Any) {
        super.destroyItem(container, position, paramterObject)
        container.removeView(paramterObject as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val rootView = LayoutInflater.from(context).inflate(R.layout.layout_topic_item, container, false)
        GlideUtils.loadImage(context, imageList[position], rootView.mTopicIv)
        container.addView(rootView)
        return rootView
    }

}