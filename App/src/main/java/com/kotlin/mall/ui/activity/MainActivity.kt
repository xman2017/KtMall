package com.kotlin.mall.ui.activity

import android.support.v4.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.kotlin.baselibrary.ui.activity.BaseActivity
import com.kotlin.mall.R
import com.kotlin.mall.ui.fragment.HomeFragment
import com.kotlin.mall.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    private val mStack: Stack<Fragment> = Stack()
    private val mHomeFragment: HomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment: HomeFragment by lazy { HomeFragment() }
    private val mCartFragment: HomeFragment by lazy { HomeFragment() }
    private val mMsgFragment: HomeFragment by lazy { HomeFragment() }
    private val mMeFragment: MeFragment by lazy { MeFragment() }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initFragments()
        initBottomNavigationListener()
        changeFragment(0)
        checkBadge()
    }

    private fun checkBadge() {
        mBottomNavigationBar.checkCartBadge(0)
        mBottomNavigationBar.checkMsgBadge(true)
    }

    private fun initBottomNavigationListener() {
        mBottomNavigationBar.setTabSelectedListener(object:BottomNavigationBar.OnTabSelectedListener{
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
    }

    private fun changeFragment(selectedPos:Int) {
        var switchTransaction = supportFragmentManager.beginTransaction()
        for(fragment in mStack){
            switchTransaction.hide(fragment)
        }
        switchTransaction.show(mStack[selectedPos])
        switchTransaction.commit()
    }

    private fun initFragments() {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.flContainer,mHomeFragment)
        transaction.add(R.id.flContainer,mCategoryFragment)
        transaction.add(R.id.flContainer,mCartFragment)
        transaction.add(R.id.flContainer,mMsgFragment)
        transaction.add(R.id.flContainer,mMeFragment)
        transaction.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)
    }

}
