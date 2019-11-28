package com.kotlin.baselibrary.utils

import com.kotlin.baselibrary.common.BaseConstants
import com.tencent.mmkv.MMKV

/**
 *
 * @Author:         xman
 * @CreateDate:     2019/11/27 17:32
 * @Description:    tencent kv component
 *
 */
object MmkvUtils {

    val instance: MMKV = MMKV.defaultMMKV()

    /*
       Boolean数据
    */
    fun putBool(key: String, value: Boolean) {
        instance.encode(key, value)
    }

    /*
        默认 false
     */
    fun getBool(key: String, defValue: Boolean): Boolean {
        return instance.decodeBool(key, defValue)
    }

    /*
        String数据
     */
    fun putString(key: String, value: String) {
        instance.encode(key, value)
    }

    /*
        默认 ""
     */
    fun getString(key: String): String {
        return getString(key, "")
    }

    /*
        默认 ""
     */
    fun getString(key: String, defValue: String): String {
        return instance.decodeString(key, defValue)
    }


    /*
        Int数据
     */
    fun putInt(key: String, value: Int) {
        instance.encode(key, value)
    }

    /*
        默认 0
     */
    fun getInt(key: String): Int {
        return instance.decodeInt(key)
    }

    /*
        Long数据
     */
    fun putLong(key: String, value: Long) {
        instance.encode(key, value)
    }

    /*
        默认 0
     */
    fun getLong(key: String): Long {
        return instance.decodeLong(key)
    }

    /*
        Set数据
     */
    fun putStringSet(key: String, set: Set<String>) {
        val localSet = getStringSet(key).toMutableSet()
        localSet.addAll(set)
        instance.encode(key, localSet)
    }

    /*
        默认空set
     */
    fun getStringSet(key: String): Set<String> {
        return instance.decodeStringSet(key, setOf<String>())
    }

    /*
        删除key数据
     */
    fun remove(key: String) {
        instance.removeValueForKey(key)
    }

}