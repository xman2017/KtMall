package com.kotlin.user.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.InvokeParam
import com.jph.takephoto.model.TContextWrap
import com.jph.takephoto.model.TResult
import com.jph.takephoto.permission.InvokeListener
import com.jph.takephoto.permission.PermissionManager
import com.jph.takephoto.permission.PermissionManager.TPermissionType
import com.jph.takephoto.permission.TakePhotoInvocationHandler
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.baselibrary.common.BaseConstants
import com.kotlin.baselibrary.ext.onClick
import com.kotlin.baselibrary.ui.activity.BaseMvpActivity
import com.kotlin.provider.common.ProviderConstants
import com.kotlin.user.R
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.UserInfoPresenter
import com.kotlin.user.presenter.view.UserInfoView
import com.kotlin.user.utils.UserPrefsUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File


class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener, InvokeListener {

    lateinit var mTakePhoto: TakePhoto
    lateinit var mTempFile: File
    lateinit var invokeParam: InvokeParam
    private var mSelectFilePath: String? = null
    private var mRemotePath: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mGender: String? = null
    private var mUserMobile: String? = null
    private var mSign: String? = null

    private val uploadManager: UploadManager by lazy { UploadManager() }

    override fun getLayoutId(): Int {
        return R.layout.activity_user_info
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mBaseView = this
    }

    override fun initBundle(savedInstanceState: Bundle?) {
        super.initBundle(savedInstanceState)
        mTakePhoto = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto
        mTakePhoto.onCreate(savedInstanceState)
    }

    override fun initView() {
        mUserIconIv.onClick { showAlertView() }
        mHeaderBar.getRightView().onClick {
            mBasePresenter.eidtUser(mUserIcon ?: "",
                    mUserNameEt.text.toString() ,
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text.toString())
        }
    }


    override fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstants.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstants.KEY_SP_USER_NAME)
        mGender = AppPrefsUtils.getString(ProviderConstants.KEY_SP_USER_GENDER)
        mUserMobile = AppPrefsUtils.getString(ProviderConstants.KEY_SP_USER_MOBILE)
        mSign = AppPrefsUtils.getString(ProviderConstants.KEY_SP_USER_SIGN)

        if (mUserIcon != "") GlideUtils.loadImage(this, mUserIcon!!, mUserIconIv)
        mUserNameEt.setText(mUserName)
        if (mGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }
        mUserMobileTv.text = mUserMobile
        mUserSignEt.setText(mSign)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mTakePhoto.onSaveInstanceState(outState)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //以下代码为处理Android6.0、7.0动态权限所需
        val type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this)
    }

    override fun invoke(invokeParam: InvokeParam): TPermissionType {
        val type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod())
        if (TPermissionType.WAIT == type) {
            this.invokeParam = invokeParam
        }
        return type
    }


    private fun showAlertView() {
        AlertView("选择头像", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, object : OnItemClickListener {
            override fun onItemClick(o: Any?, position: Int) {
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), true)
                when (position) {
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                    }
                    1 -> mTakePhoto.onPickFromGallery()
                }
            }
        }).show()

    }

    fun createTempFile() {
        var tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }
        mTempFile = File(cacheDir, tempFileName)
    }


    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("xman", msg)
    }

    override fun takeSuccess(result: TResult?) {
        mSelectFilePath = result?.image?.compressPath
        mBasePresenter.getUploadToken()
    }

    override fun onGetUploadTokenResult(result: String) {
        uploadManager.put(mSelectFilePath, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemotePath = BaseConstants.IMAGE_SERVER_ADDRESS + response?.get("hash")
                runOnUiThread(Runnable {
                    GlideUtils.loadImage(this@UserInfoActivity, mRemotePath!!, mUserIconIv)
                    mUserIcon = mRemotePath
                })
            }
        }, null)
    }

    override fun onEditUserResult(userInfo: UserInfo) {
        UserPrefsUtils.putUserInfo(userInfo)
        toast("用户数据修改成功")
    }

}
