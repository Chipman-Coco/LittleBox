package com.chipman.littlebox.wanandroid.ui.home.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.chipman.littlebox.wanandroid.adapter.HomeBannerAdapter
import com.chipman.littlebox.wanandroid.databinding.ItemHomeBannerLayoutBinding
import com.chipman.littlebox.wanandroid.ui.ViewBindingHolder
import com.chipman.model.wanandroid.Banner
import com.chipman.model.wanandroid.Banners
import com.chipman.multitype.PagingItemViewBinder
import com.youth.banner.indicator.CircleIndicator

class HomeBannerItemBinder(
    onClick: (Banner, Int) -> Unit
) : PagingItemViewBinder<Banners, ViewBindingHolder<ItemHomeBannerLayoutBinding>>() {

    private val bannerAdapter = HomeBannerAdapter(emptyList(), onClick)

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewBindingHolder<ItemHomeBannerLayoutBinding> {
        return ViewBindingHolder(
            ItemHomeBannerLayoutBinding.inflate(inflater, parent, false).apply {
                with(banner) {
                    setAdapter(bannerAdapter)
                    indicator = CircleIndicator(context)
                    addBannerLifecycleObserver(findViewTreeLifecycleOwner())
                }
            }
        )
    }

    override fun onBindViewHolder(
        holder: ViewBindingHolder<ItemHomeBannerLayoutBinding>,
        item: Banners
    ) {
        bannerAdapter.setDatas(item.banners)
    }
}