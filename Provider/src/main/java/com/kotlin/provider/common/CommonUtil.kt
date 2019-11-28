package com.kotlin.provider.common

import com.kotlin.baselibrary.common.BaseConstants
import com.kotlin.baselibrary.utils.MmkvUtils

/**
 *
 * @Author:         xman
 * @CreateDate:     2019/11/28 14:59
 * @Description:
 *
 */
fun isLogined():Boolean{
    return MmkvUtils.getString(BaseConstants.KEY_SP_TOKEN).isNotEmpty()
}