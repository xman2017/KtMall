package com.kotlin.user.utils

import com.kotlin.baselibrary.common.BaseConstants
import com.kotlin.baselibrary.utils.MmkvUtils
import com.kotlin.provider.common.ProviderConstants
import com.kotlin.user.data.protocol.UserInfo

/*
    本地存储用户相关信息
 */
object UserPrefsUtils {

    /*
        退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        MmkvUtils.putString(BaseConstants.KEY_SP_TOKEN, userInfo?.id ?: "")
        MmkvUtils.putString(ProviderConstants.KEY_SP_USER_ICON, userInfo?.userIcon ?: "")
        MmkvUtils.putString(ProviderConstants.KEY_SP_USER_NAME, userInfo?.userName ?: "")
        MmkvUtils.putString(ProviderConstants.KEY_SP_USER_MOBILE, userInfo?.userMobile ?: "")
        MmkvUtils.putString(ProviderConstants.KEY_SP_USER_GENDER, userInfo?.userGender ?: "")
        MmkvUtils.putString(ProviderConstants.KEY_SP_USER_SIGN, userInfo?.userSign ?: "")
    }
}
