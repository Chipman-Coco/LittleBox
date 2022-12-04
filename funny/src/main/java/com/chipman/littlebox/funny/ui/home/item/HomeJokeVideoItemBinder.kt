package com.chipman.littlebox.funny.ui.home.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chipman.littlebox.funny.databinding.ItemJokeVideoLayoutBinding
import com.chipman.littlebox.funny.service.model.Joke
import com.chipman.multitype.PagingItemViewBinder

/**
 *
 */
class HomeJokeVideoItemBinder : PagingItemViewBinder<Joke, ViewBindingHolder>() {

    override fun onBindViewHolder(holder: ViewBindingHolder, item: Joke) {
        with(holder.binding) {

        }
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewBindingHolder {
        return ViewBindingHolder(ItemJokeVideoLayoutBinding.inflate(inflater, parent, false))
    }
}

class ViewBindingHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)