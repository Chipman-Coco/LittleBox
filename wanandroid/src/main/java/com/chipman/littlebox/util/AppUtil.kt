package com.chipman.littlebox.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.chipman.littlebox.App
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

object AppUtil {

    /**
     * 获取App版本号
     * @return Long App版本号
     */
    fun getVersionCode(): Long {
        val packageInfo = App.mContext.packageManager.getPackageInfo(App.mContext.packageName, 0)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            packageInfo.longVersionCode
        } else {
            packageInfo.versionCode.toLong()
        }
    }

    /**
     * 获取App版本名
     * @return String App版本名
     */
    fun getVersionName(): String {
        return App.mContext
            .packageManager
            .getPackageInfo(App.mContext.packageName, 0)
            .versionName
    }

    /**
     * 收起键盘
     */
    fun hideSoftKeyboard(context: Context, view: View?) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (view != null) {
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 倒计时(需要处理生命周期问题)
     * @param total 时长
     * @param onTick 每次需要执行的函数
     * @param onFinish 结束时执行的函数
     * @param coroutineScope 运行的协程作用域，默认 MainScope
     */
    fun countDow(
        total: Int,
        onTick: (Int) -> Unit,
        onFinish: () -> Unit,
        coroutineScope: CoroutineScope = MainScope()
    ): Job {
        return flow {
            for (i in total downTo 0) {
                emit(i)
                delay(1000)
            }
        }.flowOn(Dispatchers.Default)
            .onCompletion { onFinish.invoke() }
            .onEach { onTick.invoke(it) }
            .flowOn(Dispatchers.Main)
            .launchIn(coroutineScope)
        /**
        待测试：失败
        调用出可以尝试使用job.cancelAndJoin()，等待执行完成就取消，
        并在activity/fragment 的onPause/onStop中调用job.cancel()来取消任务，
        防止退出应用后任然会执行onCompletion中的操作
         */
//        return (total.downTo(0)).asFlow()
//            .cancellable() //不确定会不会执行onCompletion
//            .onCompletion { onFinish.invoke() }
//            .onEach {
//                delay(1000)
//                onTick.invoke(it)
//            }
//            .flowOn(Dispatchers.Default)
//            .launchIn(coroutineScope)
    }

    /**
     * 加载视频第一帧
     * @param videoUrl String
     * @return Bitmap?
     */
    suspend fun getVideoThumb(videoUrl: String) = withContext(Dispatchers.IO) {
        val deferred = async {
            var bitmap: Bitmap? = null
            val retriever = MediaMetadataRetriever()
            try {
                //根据url获取缩略图
                retriever.setDataSource(videoUrl, hashMapOf())
                //获得第一帧图片
                bitmap = retriever.frameAtTime
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } finally {
                retriever.release()
            }
            bitmap
        }
        deferred.await()
    }

    /**
     * 前几天日期
     */
    fun calendar(amount: Int): String {
        val days: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localDate = LocalDate.now()
            val minusTime: LocalDate = if (amount > 0) {
                localDate.plusDays(amount.toLong())
            } else if (amount < 0) {
                localDate.minusDays(Math.abs(amount).toLong())
            } else {
                localDate
            }
            days = minusTime.toString()
        } else {
            val calendar1 = Calendar.getInstance()
            val sdf1 = SimpleDateFormat("yyyy-MM-dd")
            calendar1.add(Calendar.DATE, amount)
            days = sdf1.format(calendar1.time)
        }
        val data = days.split("-").toTypedArray()
        return data[1] + "-" + data[2]
    }

    fun calendars(amount: Int): String {
        val days: String
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localDate = LocalDate.now()
            val minusTime: LocalDate = if (amount > 0) {
                localDate.plusDays(amount.toLong())
            } else if (amount < 0) {
                localDate.minusDays(Math.abs(amount).toLong())
            } else {
                localDate
            }
            days = minusTime.toString()
        } else {
            val calendar1 = Calendar.getInstance()
            val sdf1 = SimpleDateFormat("yyyy-MM-dd")
            calendar1.add(Calendar.DATE, amount)
            days = sdf1.format(calendar1.time)
        }
        val data = days.split("-").toTypedArray()
        return data[0] + data[1] + data[2]
    }

    //复制
    fun copyText(context: Context, str: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("", str)
        clipboard.setPrimaryClip(clip)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
            Toast.makeText(context, "已复制", Toast.LENGTH_SHORT).show()
    }

}