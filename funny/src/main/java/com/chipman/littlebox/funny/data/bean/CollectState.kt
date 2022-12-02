package com.chipman.littlebox.funny.data.bean

import com.google.gson.annotations.SerializedName

/**
 * 收藏状态
 */
data class CollectState(
    @SerializedName("is_collect")
    val isCollect: Boolean      // 是否收藏
)
