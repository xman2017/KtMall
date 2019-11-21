package com.kotlin.baselibrary.data.protocol

/**
 *
 * author：xman
 * created on: 2019/6/13 3:58 PM
 * description:
 *
 */
class BaseResp<out T>(val status:Int, val message:String, val data:T) {
}