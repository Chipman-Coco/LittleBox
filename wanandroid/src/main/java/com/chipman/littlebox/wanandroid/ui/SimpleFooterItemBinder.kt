package com.chipman.littlebox.wanandroid.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import com.chipman.littlebox.wanandroid.databinding.ItemPagingFooterBinding
import com.chipman.multitype.FooterStateItemBinder

open class SimpleFooterItemBinder :
    FooterStateItemBinder<ViewBindingHolder<ItemPagingFooterBinding>>() {

    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup
    ): ViewBindingHolder<ItemPagingFooterBinding> =
        ViewBindingHolder(
            ItemPagingFooterBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: ViewBindingHolder<ItemPagingFooterBinding>,
        state: LoadState
    ) {
        holder.binding.apply {
            if (state is LoadState.Error) {
                errorMsg.text = state.error.localizedMessage
            }
            loadingProgress.isVisible = state is LoadState.Loading
            retryButton.isVisible = state is LoadState.Error
            errorMsg.isVisible = state is LoadState.Error
            endTips.isVisible = state.endOfPaginationReached
        }
    }
}