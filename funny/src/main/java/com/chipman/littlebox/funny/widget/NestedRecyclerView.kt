package com.chipman.littlebox.funny.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * RecyclerView滑动到底部或顶部时，防止事件被ViewPager2拦截
 */
class NestedRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var eventX = 0f
    private var eventY = 0f

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        e ?: return super.onTouchEvent(e)
        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                eventX = e.x
                eventY = e.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = e.x - eventX
                val dy = e.y - eventY
                if (!canScrollVertically(1) || !canScrollVertically(-1)) {
                    if (abs(dx) < abs(dy)) {
                        requestDisallowInterceptTouchEvent(true)
                    }
                }
            }
        }
        return super.onTouchEvent(e)
    }
}