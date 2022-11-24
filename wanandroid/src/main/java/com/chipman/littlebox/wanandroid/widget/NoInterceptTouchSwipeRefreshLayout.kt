package com.chipman.littlebox.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class NoInterceptTouchSwipeRefreshLayout(
    context: Context,
    attrs: AttributeSet?
) : SwipeRefreshLayout(context, attrs) {

    /**
     * 不拦截任何子View事件
     */
    override fun onInterceptHoverEvent(event: MotionEvent?) = false
}