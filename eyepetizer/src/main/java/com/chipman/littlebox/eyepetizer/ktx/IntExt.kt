package com.chipman.littlebox.eyepetizer.ktx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 解析xml布局
 *
 * @param parent 父布局
 * @param attachToRoot 是否依附到父布局
 */
fun Int.inflate(parent: ViewGroup, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(parent.context).inflate(this, parent, attachToRoot)
}