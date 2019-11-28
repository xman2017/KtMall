package com.kotlin.mall.ui.adapter

import android.content.Context
import android.graphics.Paint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.base.utils.GlideUtils
import com.kotlin.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.kotlin.mall.R
import com.kotlin.mall.ui.adapter.HomeDiscountAdapter.HomeDiscountViewHolder
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 *
 * @Author:         xman
 * @CreateDate:     2019/11/28 10:57
 * @Description:
 *
 */
class HomeDiscountAdapter<String, HomeDiscountViewHolder>(context: Context) : BaseRecyclerViewAdapter<String, RecyclerView.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_home_discount_item, parent, false)
        return HomeDiscountViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        GlideUtils.loadImage(mContext, dataList[position].toString(), holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text = "$123"
        holder.itemView.mDiscountBeforeTv.text = "$999"
    }

    class HomeDiscountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            itemView.mDiscountBeforeTv.paint.isAntiAlias = true
        }
    }
}