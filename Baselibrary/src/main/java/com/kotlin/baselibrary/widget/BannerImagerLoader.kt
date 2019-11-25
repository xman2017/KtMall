package com.kotlin.baselibrary.widget

import android.content.Context
import android.widget.ImageView
import com.kotlin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 *
 * author：xman
 * created on: 2019-11-25 21:29
 * description:
 *
 */
class BannerImagerLoader: ImageLoader() {
    override fun displayImage(context: Context, path: Any?, imageView: ImageView?) {
        GlideUtils.loadImage(context,path.toString(), imageView!!)
    }
}