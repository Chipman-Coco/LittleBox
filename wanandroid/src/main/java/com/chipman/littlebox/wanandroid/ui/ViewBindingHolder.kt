package com.chipman.littlebox.wanandroid.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class ViewBindingHolder<VB : ViewBinding>(open val binding: VB) :
    RecyclerView.ViewHolder(binding.root)