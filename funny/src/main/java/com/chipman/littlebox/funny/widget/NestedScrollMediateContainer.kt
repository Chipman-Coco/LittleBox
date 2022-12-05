package com.chipman.littlebox.funny.widget

/**
 * RecyclerView滑动到底部或顶部时，防止事件被ViewPager2拦截
 */
/*
class NestedScrollMediateContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var eventX = 0f
    private var eventY = 0f
    private val childView by lazy(LazyThreadSafetyMode.NONE) {
        children.first()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        ev ?: return super.onInterceptTouchEvent(ev)
        when(ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                eventX = ev.x
                eventY = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = ev.x - eventX
                val dy = ev.y - eventY
                if (!childView.canScrollVertically(1) || !childView.canScrollVertically(-1)) {
                    if (abs(dx) < abs(dy)) {
                        requestDisallowInterceptTouchEvent(true)
                    }
                }
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
}*/
