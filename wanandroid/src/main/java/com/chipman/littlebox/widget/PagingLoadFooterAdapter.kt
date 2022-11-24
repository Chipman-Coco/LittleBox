package com.chipman.littlebox.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chipman.littlebox.wanandroid.R
import timber.log.Timber

class PagingLoadFooterAdapter(private val retry: () -> Unit) : LoadStateAdapter<PagingLoadFooterAdapter.FooterViewHolder>() {

    class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val progressBar: ProgressBar = view.findViewById(R.id.progress_view)
        val tipText: TextView = view.findViewById(R.id.load_text)
    }

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        Timber.d("loadState: $loadState")
        holder.progressBar.isVisible = loadState is LoadState.Loading
        when (loadState) {
            is LoadState.Loading -> {
                holder.tipText.text = "加载中..."
            }
            is LoadState.NotLoading -> {
                holder.tipText.text = "没有更多了~"
            }
            is LoadState.Error -> {
                holder.tipText.text = "加载失败，点击重试~"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paging_footer, parent, false)
        val holder = FooterViewHolder(view)
        holder.itemView.setOnClickListener {
            retry.invoke()
        }
        return holder
    }

    /**
     * 解决 LoadState == NotLoading 时不显示Footer问题
     */
    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading ||
                loadState is LoadState.Error ||
                (loadState is LoadState.NotLoading && loadState.endOfPaginationReached)
    }
}