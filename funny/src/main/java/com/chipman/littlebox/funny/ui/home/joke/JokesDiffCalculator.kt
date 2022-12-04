package com.chipman.littlebox.funny.ui.home.joke

import com.chipman.littlebox.funny.service.model.RecommendInfo
import com.chipman.littlebox.funny.widget.diff.SimpleDiffItemCallback

object JokesDiffCalculator {

    fun getJokesItemDiffCallback() =
        SimpleDiffItemCallback(
            areItemSame = { oldItem: Any, newItem: Any ->
                when {
                    oldItem is RecommendInfo && newItem is RecommendInfo -> oldItem.joke.jokesId == newItem.joke.jokesId
                    else -> oldItem::class.java == newItem::class.java
                }
            },
            areContentSame = { oldItem: Any, newItem: Any ->
                oldItem is RecommendInfo && newItem is RecommendInfo && oldItem == newItem
            }
        )
}