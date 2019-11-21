package com.kotlin.baselibrary.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 *
 * author：xman
 * created on: 2019/6/20 11:00 AM
 * description: activity管理类
 *
 */
class AppManager private constructor() {

    private var activityStatck: Stack<Activity> = Stack()

    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    /**
     *  进栈
     */
    fun addActivity(activity: Activity) {
        activityStatck.add(activity)
    }

    /**
     *出栈
     */
    fun finishActivity(activity: Activity) {
        activity.finish()
        activityStatck.remove(activity)
    }

    /**
     * 关闭所有activity
     */
    fun finishAllActivity() {
        for (activity: Activity in activityStatck) {
            activity.finish()
        }
        activityStatck.clear()
    }

    /**
     * 获取当前栈顶的activity
     */
    fun currentActivity():Activity{
        return activityStatck.lastElement()
    }


    /**
     * 退出当前应用程序
     */
    fun exitApp(context: Context){
        finishAllActivity()
        var activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}