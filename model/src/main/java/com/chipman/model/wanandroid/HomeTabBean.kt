package com.chipman.model.wanandroid

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeTabBean(
    val title: String
) : Parcelable