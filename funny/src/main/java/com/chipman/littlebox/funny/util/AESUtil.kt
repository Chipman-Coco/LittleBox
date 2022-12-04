package com.chipman.littlebox.funny.util

import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.EncryptUtils
import timber.log.Timber

/**
 * 图片、视频 URL处理
 * 1、拿到链接后，先去除掉头部 ftp://
 * 2、AES对称加密，密钥临时为 cretinzp**273846
 * 3、剩下的内容再解密
 */
object AESUtil {

    private const val KEY = "cretinzp**273846"

    fun decrypt(url: String): String {
        val byteUrl = url.replaceFirst("ftp://", "").toByteArray()
        val decryptByte = EncryptUtils.decryptBase64AES(byteUrl, KEY.toByteArray(), "AES/ECB/PKCS5Padding", null)
        val decryptResult = ConvertUtils.bytes2String(decryptByte)
//        Timber.d("decryptResult: $decryptResult")
        return decryptResult
    }
}