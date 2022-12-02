package com.chipman.littlebox.funny.util

import android.annotation.SuppressLint
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.text.TextUtils
import com.chipman.littlebox.funny.App
import timber.log.Timber
import java.util.*

object GlobalUtil {

    private var deviceSerial: String? = null // 缓存的设备序列号

    /**
     * 获取当前应用程序的包名。
     * @return 当前应用程序的包名。
     */
    val appPackage: String
        get() = App.mContext.packageName

    /**
     * 获取当前应用程序的名称。
     * @return 当前应用程序的名称。
     */
    val appName: String
        get() = App.mContext.resources.getString(App.mContext.applicationInfo.labelRes)

    /**
     * 获取当前应用程序的版本名。
     * @return 当前应用程序的版本名。
     */
    val appVersionName: String
        get() = App.mContext.packageManager.getPackageInfo(appPackage, 0).versionName

    /**
     * 获取当前应用程序的版本号。
     * @return 当前应用程序的版本号。
     */
    val appVersionCode: Long
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            App.mContext.packageManager.getPackageInfo(appPackage, 0).longVersionCode
        } else {
            App.mContext.packageManager.getPackageInfo(appPackage, 0).versionCode.toLong()
        }

    /**
     * 获取设备的的型号，如果无法获取到，则返回Unknown。
     * @return 设备型号
     */
    val deviceModel: String
        get() {
            var deviceModel = Build.MODEL
            if (TextUtils.isEmpty(deviceModel)) {
                deviceModel = "unknown"
            }
            return deviceModel
        }

    /**
     * 获取设备的品牌，如果无法获取到，则返回Unknown。
     * @return 设备品牌，全部转换为小写格式 (例如：honor)
     */
    val deviceBrand: String
        get() {
            var deviceBrand = Build.BRAND
            if (TextUtils.isEmpty(deviceBrand)) {
                deviceBrand = "unknown"
            }
            return deviceBrand/*.lowercase(Locale.getDefault())*/
        }

    /**
     * 获取设备的序列号。如果无法获取到设备的序列号，则会生成一个随机的UUID来作为设备的序列号，UUID生成之后会存入缓存，
     * 下次获取设备序列号的时候会优先从缓存中读取。
     * @return 设备的序列号。
     */
    @SuppressLint("HardwareIds")
    fun getDeviceSerial(): String {
        if (deviceSerial == null) {
            var deviceId: String? = null
            val appChannel = getApplicationMetaData("APP_CHANNEL")
            Timber.d("appChannel: $appChannel")
            if ("google" != appChannel || "samsung" != appChannel) {
                try {
                    deviceId = Settings.Secure.getString(App.mContext.contentResolver, Settings.Secure.ANDROID_ID)
                } catch (e: Exception) {
                    Timber.e("get android_id with error", e)
                }
                if (!TextUtils.isEmpty(deviceId) && deviceId!!.length < 255) {
                    deviceSerial = deviceId
                    return deviceSerial.toString()
                }
            }
            var uuid = SpUtil.getString("uuid", "")
            if (!TextUtils.isEmpty(uuid)) {
                deviceSerial = uuid
                return deviceSerial.toString()
            }
            uuid = UUID.randomUUID().toString().replace("-", "").uppercase(Locale.getDefault())
            SpUtil.putString("uuid", uuid)
            deviceSerial = uuid
            return deviceSerial.toString()
        } else {
            return deviceSerial.toString()
        }
    }

    /**
     * 获取设备系统版本号（例如：10）
     * @return String?
     */
    fun getSDKVersionName(): String? {
        return Build.VERSION.RELEASE
    }

    /**
     * 获取设备系统版本码（例如：29）
     * @return Int
     */
    fun getSDKVersionCode(): Int {
        return Build.VERSION.SDK_INT
    }

    /**
     * 判断某个应用是否安装。
     * @param packageName
     * 要检查是否安装的应用包名
     * @return 安装返回true，否则返回false。
     */
    fun isInstalled(packageName: String): Boolean {
        val packageInfo: PackageInfo? = try {
            App.mContext.packageManager.getPackageInfo(packageName, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
        return packageInfo != null
    }

    /**
     * 获取AndroidManifest.xml文件中，<application>标签下的meta-data值。
     * @param key
     *  <application>标签下的meta-data健
     */
    private fun getApplicationMetaData(key: String): String? {
        var applicationInfo: ApplicationInfo? = null
        try {
            applicationInfo = App.mContext.packageManager.getApplicationInfo(appPackage, PackageManager.GET_META_DATA)
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.e(e.message, e)
        }
        if (applicationInfo == null) return ""
        return applicationInfo.metaData.getString(key)
    }
}