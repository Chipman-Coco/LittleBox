package com.chipman.littlebox.funny.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerLinearDivider private constructor(context: Context) : RecyclerView.ItemDecoration() {

    companion object {
        // 分割线属性
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
        const val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        const val VERTICAL_LIST = LinearLayoutManager.VERTICAL
    }

    private var mContext: Context? = null

    // 分割线绘制所需要的 Drawable ，也可以直接使用 Canvas 绘制
    private var mDivider: Drawable? = null

    private var mOrientation: Int = VERTICAL_LIST

    init {
        mContext = context
        val a = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
    }

    /**
     * 设置分割线样式
     * @param resourceId Int
     */
    fun setDividerDrawable(resourceId: Int) {
        mDivider = mContext?.getDrawable(resourceId)
    }

    private var keepMargin: Boolean = false

    /**
     * 保持外边距
     */
    fun keepMargin(keep: Boolean) {
        this.keepMargin = keep
    }

    /**
     * 设置方向，如果是 RecyclerView 是上下方向，那么这里设置 VERTICAL_LIST ，否则设置 HORIZONTAL_LIST
     * @param orientation 方向
     */
    private fun setOrientation(orientation: Int) {
        // 传入的值必须是预先定义好的
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw IllegalArgumentException("invalid orientation")
        }
        mOrientation = orientation
    }

    /**
     * 开始绘制，这个函数只会执行一次，
     * 所以在绘制的时候需要在这里把所有项的都绘制，
     * 而不是只处理某一项
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = if (keepMargin) parent.paddingTop else 0
        val bottom = if (keepMargin) parent.height - parent.paddingBottom else parent.height
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + (mDivider?.intrinsicWidth ?: 0)
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(c)
        }
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        // 左边的距离，
        // 意思是左边从哪儿开始绘制，
        // 对于每一项来说，
        // 肯定需要将 RecyclerView 的左边的 paddingLeft 给去掉
        val left = if (keepMargin) parent.paddingLeft else 0
        // 右边就是 RecyclerView 的宽度减去 RecyclerView 右边设置的 paddingRight 值
        val right = if (keepMargin) parent.width - parent.paddingRight else parent.width
        // 获取当前 RecyclerView 下总共有多少 Item
        val childCount = parent.childCount
        // 循环把每一项的都绘制完成，如果最后一项不需要，那么这里的循环就少循环一次
        for (i in 0 until childCount - 1) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            // 上边的距离就是当前 Item 下边再加上本身设置的 marginBottom
            val top = child.bottom + params.bottomMargin
            // 下边就简单了，就是上边 + 分割线的高度
            val bottom = top + (mDivider?.intrinsicHeight ?: 0)
            mDivider?.setBounds(left, top, right, bottom)
            mDivider?.draw(c)
        }
    }

    // 这个函数会被反复执行，执行的次数跟 Item 的个数相同
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        // 由于在上面的距离绘制，但是实际上那里不会主动为我们绘制腾出空间，
        // 需要重写这个函数来手动调整空间，给上面的绘制不会被覆盖
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, mDivider?.intrinsicHeight ?: 0)
        } else {
            outRect.set(0, 0, mDivider?.intrinsicWidth ?: 0, 0)
        }
    }

     class Builder(context: Context) {

        private val mRecyclerLinearDivider = RecyclerLinearDivider(context)

        fun setOrientation(orientation: Int): Builder {
            mRecyclerLinearDivider.setOrientation(orientation)
            return this
        }

        fun setDivider(@DrawableRes resourceId: Int): Builder {
            mRecyclerLinearDivider.setDividerDrawable(resourceId)
            return this
        }

        fun keepMargin(keep: Boolean): Builder {
            mRecyclerLinearDivider.keepMargin(keep)
            return this
        }

        fun create(): RecyclerLinearDivider {
            return mRecyclerLinearDivider
        }
    }
}