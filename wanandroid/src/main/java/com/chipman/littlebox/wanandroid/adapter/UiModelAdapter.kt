package com.chipman.littlebox.wanandroid.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.chipman.domain.action.ArticleAction
import com.chipman.littlebox.wanandroid.R
import com.chipman.littlebox.wanandroid.databinding.ItemHomeArticleLayoutBinding
import com.chipman.littlebox.wanandroid.databinding.ItemHomeBannerLayoutBinding
import com.chipman.model.wanandroid.*
import timber.log.Timber

class UiModelAdapter(
    private val onClick: (ArticleAction) -> Unit
) : PagingDataAdapter<Any, BaseItemViewHolder>(UiModelComparator) {

    override fun getItemViewType(position: Int): Int {
        Timber.d("itemType: ${peek(position)?.javaClass}, position: $position")
        return when (peek(position)) {
            is Article -> R.layout.item_home_article_layout
            is Classify -> R.layout.item_home_article_layout
            is Navigation -> R.layout.item_home_article_layout
            is Series -> R.layout.item_home_article_layout
            is Banners -> R.layout.item_home_banner_layout
            else -> throw IllegalStateException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: BaseItemViewHolder, position: Int) {
        Timber.d("onBindViewHolder position: $position")
        holder.onBind(getItem(position) as? UiModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val articleViewHolder = ArticleViewHolder(ItemHomeArticleLayoutBinding.inflate(inflater, parent, false), onClick)
        return when (viewType) {
            R.layout.item_home_article_layout -> articleViewHolder
            R.layout.item_home_banner_layout -> BannerViewHolder(
                ItemHomeBannerLayoutBinding.inflate(
                    inflater,
                    parent,
                    false
                )/*,
                onClick*/
            )
            else -> throw Throwable("Unknown viewType")
        }
    }
}

object UiModelComparator : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(
        oldItem: Any,
        newItem: Any
    ): Boolean {
        return when {
            oldItem is Navigation && newItem is Navigation -> oldItem.name == newItem.name
            oldItem is Series && newItem is Series -> oldItem.id == newItem.id
            oldItem is Classify && newItem is Classify -> oldItem.id == newItem.id
            oldItem is Article && newItem is Article -> oldItem.id == newItem.id
//            oldItem is Banners && newItem is Banners -> oldItem == newItem
            else -> oldItem.javaClass == newItem.javaClass
        }
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: Any,
        newItem: Any
    ) = when {
        oldItem is Navigation && newItem is Navigation -> oldItem == newItem
        oldItem is Series && newItem is Series -> oldItem == newItem
        oldItem is Classify && newItem is Classify -> oldItem == newItem
        oldItem is Article && newItem is Article -> oldItem == newItem
//        oldItem is Banners && newItem is Banners -> oldItem == newItem
        else -> oldItem.javaClass == newItem.javaClass && oldItem == newItem
    }
}