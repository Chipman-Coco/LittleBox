package com.chipman.littlebox.wanandroid.ui.home.item

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.chipman.domain.action.ArticleAction
import com.chipman.littlebox.wanandroid.R
import com.chipman.littlebox.wanandroid.databinding.ItemHomeArticleLayoutBinding
import com.chipman.littlebox.wanandroid.ui.ViewBindingHolder
import com.chipman.model.wanandroid.Article
import com.chipman.multitype.PagingItemViewBinder

class HomeArticleItemBinder(
    private val onClick: (ArticleAction) -> Unit
) : PagingItemViewBinder<Article, ViewBindingHolder<ItemHomeArticleLayoutBinding>>() {

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewBindingHolder<ItemHomeArticleLayoutBinding> {
        return ViewBindingHolder(
            ItemHomeArticleLayoutBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewBindingHolder<ItemHomeArticleLayoutBinding>, item: Article) {
        holder.binding.apply {
            tvAuthor.text = item.getArticleAuthor()
            tvIsTop.isVisible = item.type == 1
            tvNew.isVisible = item.fresh
            tvTag1.text = item.tags.elementAtOrNull(0)?.name
            tvTag2.text = item.tags.elementAtOrNull(1)?.name
            tvDate.text = item.niceDate
            tvContent.text = Html.fromHtml(item.title)
            tvType2.text = "${item.superChapterName}.${item.chapterName}"
            ivCollect.setImageResource(if (item.collect) R.drawable.ic_collect else R.drawable.ic_uncollect)

            root.setOnClickListener {
                onClick(ArticleAction.ItemClick(holder.bindingAdapterPosition, item))
            }
            ivCollect.setOnClickListener {
                onClick(ArticleAction.CollectClick(holder.bindingAdapterPosition, item))
            }
            tvAuthor.setOnClickListener {
                onClick(ArticleAction.AuthorClick(holder.bindingAdapterPosition, item))
            }
        }
    }
}