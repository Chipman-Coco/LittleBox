package com.chipman.littlebox.wanandroid.adapter

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chipman.model.wanandroid.Banner
import com.youth.banner.adapter.BannerAdapter

class HomeBannerAdapter(
    items: List<Banner>,
//    private val onClick: (Banner?, Int) -> Unit
) : BannerAdapter<Banner, HomeBannerAdapter.BannerItemViewHolder>(items) {

    class BannerItemViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerItemViewHolder {
        val imageView = AppCompatImageView(parent!!.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return BannerItemViewHolder(imageView)
    }

    override fun onBindView(holder: BannerItemViewHolder?, data: Banner?, position: Int, size: Int) {
        (holder?.view as? AppCompatImageView)?.apply {
            Glide.with(context)
                .load(data?.imagePath)
                .into(this)
//            setOnClickListener {
//                onClick(data, position)
//            }
        }
    }
}