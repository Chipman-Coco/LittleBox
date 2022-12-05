package com.chipman.littlebox.funny.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chipman.littlebox.funny.R

/**
 * 不拦截子View任何事件
 */
class NoInterceptTouchSwipeRefreshLayout(context: Context, attrs: AttributeSet?) :
    SwipeRefreshLayout(context, attrs) {

        init {
            setColorSchemeColors(context.getColor(R.color.material_blue_400))
//            setProgressBackgroundColorSchemeColor(context.getColor(R.color.white))
        }

    override fun onInterceptTouchEvent(ev: MotionEvent?) = false
}