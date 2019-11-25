package com.kotlin.baselibrary.widget

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.kotlin.baselibrary.R

/**
 *
 * @Author:         xman
 * @CreateDate:     2019/11/25 17:32
 * @Description:    底部导航栏
 *
 */
class BottomNavigationBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    var cartBadgeItem: TextBadgeItem
    var msgBadgeItem:ShapeBadgeItem

    init {
        var homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press, R.string.nav_bar_home)
                .setInactiveIconResource(R.drawable.btn_nav_home_normal)
                .setActiveColor(R.color.common_blue)
                .setInActiveColor(R.color.text_normal)

        var categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press, R.string.nav_bar_category)
                .setInactiveIconResource(R.drawable.btn_nav_category_normal)
                .setActiveColor(R.color.common_blue)
                .setInActiveColor(R.color.text_normal)


        var cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press, R.string.nav_bar_cart)
                .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
                .setActiveColor(R.color.common_blue)
                .setInActiveColor(R.color.text_normal)
        cartBadgeItem = TextBadgeItem()
        cartItem.setBadgeItem(cartBadgeItem)

        var msgItem = BottomNavigationItem(R.drawable.btn_nav_msg_press, R.string.nav_bar_msg)
                .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
                .setActiveColor(R.color.common_blue)
                .setInActiveColor(R.color.text_normal)
        msgBadgeItem = ShapeBadgeItem()
        msgItem.setBadgeItem(msgBadgeItem)

        var mineItem = BottomNavigationItem(R.drawable.btn_nav_user_press, R.string.nav_bar_user)
                .setInactiveIconResource(R.drawable.btn_nav_user_normal)
                .setActiveColor(R.color.common_blue)
                .setInActiveColor(R.color.text_normal)

        setMode(MODE_FIXED)
        setBackgroundStyle(BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)

        addItem(homeItem)
                .addItem(categoryItem)
                .addItem(cartItem)
                .addItem(msgItem)
                .addItem(mineItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }

    fun showCartBadge(count:Int){
        if(count == 0){
            cartBadgeItem.hide()
        }else{
            cartBadgeItem.setText("$count")
            cartBadgeItem.show()
        }
    }

    fun showMsgBadge(showBadge:Boolean){
        if (showBadge){
            msgBadgeItem.show()
        }else{
            msgBadgeItem.hide()
        }
    }
}