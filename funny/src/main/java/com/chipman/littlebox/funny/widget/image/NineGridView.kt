package com.chipman.littlebox.funny.widget.image

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import com.chipman.littlebox.funny.R

/**
 * 九宫格
 */
class NineGridView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {

    companion object {
        const val MODE_FILL = 0                 // 填充模式
        const val MODE_GRID = 1                 // 网格模式

        var mImageLoader: ImageLoader? = null   // 全局的图片加载器（必须设置）
    }

    private val columnCount: Int = 0            // 列数
    private val rowCount: Int = 0               // 行数
    private var gridWidth: Int = 0               // 宫格宽度
    private var gridHeight: Int = 0             // 宫格高度

    private var singleImageSize = 250.0f        // 单张图片时的最大大小,单位dp
    private var singleImageRatio = 1.0f         // 单张图片的宽高比(宽/高)
    private var maxImageCount = 9               // 最大显示的图片数
    private var gridSpacing = 3.0f              // 宫格间距，单位dp
    private var mode = MODE_FILL                // 默认使用fill模式

    private val imageViews by lazy { arrayListOf<ImageView>() }
    private val mImagesInfo by lazy { arrayListOf<ImageInfo>() }
    private val mAdapter: NineGridViewAdapter? = null

    init {
        val displayMetrics = context.resources.displayMetrics
        gridSpacing = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, gridSpacing, displayMetrics)
        singleImageSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, singleImageSize, displayMetrics)

        context.obtainStyledAttributes(attrs, R.styleable.NineGridView).apply {
            gridSpacing = getDimension(R.styleable.NineGridView_ngv_gridSpacing, gridSpacing)
            singleImageSize = getDimension(R.styleable.NineGridView_ngv_singleImageSize, singleImageSize)
            singleImageRatio = getFloat(R.styleable.NineGridView_ngv_singleImageRatio, singleImageRatio)
            maxImageCount = getInt(R.styleable.NineGridView_ngv_maxImageCount, maxImageCount)
            mode = getInt(R.styleable.NineGridView_ngv_mode, mode)
        }.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = 0
        val totalWidth = width - paddingLeft - paddingRight
        if (mImagesInfo.isNotEmpty()) {
            if (mImagesInfo.size == 1) {
                gridWidth = if (singleImageSize > totalWidth) totalWidth else singleImageSize.toInt()
                gridHeight = (gridWidth / singleImageRatio).toInt()
                // 矫正图片显示区域大小，不允许超过最大显示范围
                if (gridHeight > singleImageSize) {
                    val ratio = singleImageSize / gridHeight
                    gridWidth = (gridWidth * ratio).toInt()
                    gridHeight = singleImageSize.toInt()
                }
            } else {
                // 无论几张图片，宽高都按总宽度的三分之一
                gridWidth = ((totalWidth - gridSpacing * 2) / 3).toInt()
                gridHeight = gridWidth
            }
            width = (gridWidth * columnCount + gridSpacing * (columnCount - 1) + paddingLeft + paddingRight).toInt()
            height = (gridHeight * rowCount + gridSpacing * (rowCount - 1) + paddingTop + paddingBottom).toInt()
        }
        setMeasuredDimension(width, height)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (mImagesInfo.isEmpty()) return
        val childrenCount = mImagesInfo.size
        for (i in 0 until childrenCount) {
            val childrenView = getChildAt(i) as ImageView
            val rowNum = i / columnCount
            val columnNum = i % columnCount
            val left = (gridWidth + gridSpacing) * columnNum + paddingLeft
            val top = (gridHeight + gridSpacing) * rowNum + paddingTop
            val right = left + gridWidth
            val bottom = top + gridHeight
            childrenView.layout(left.toInt(), top.toInt(), right.toInt(), bottom.toInt())

            mImageLoader?.onDisplayImage(context, childrenView, mImagesInfo[i].thumbnailUrl)
        }
    }

    fun setAdapter(adapter: NineGridViewAdapter) {

    }

    interface ImageLoader {
        /**
         * 子类实现如何加载和显示图片
         */
        fun onDisplayImage(context: Context, imageView: ImageView, url: String)

        /**
         * @param url 图片地址
         * @return 本地缓存图片
         */
        fun getCacheImage(url: String): Bitmap?
    }
}