package com.chipman.littlebox.adapter

import android.annotation.SuppressLint
import android.text.Html
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chipman.domain.action.ArticleAction
import com.chipman.littlebox.R
import com.chipman.littlebox.databinding.ItemHomeArticleLayoutBinding
import com.chipman.model.wanandroid.Article
import com.chipman.model.wanandroid.UiModel

abstract class BaseItemViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(data: UiModel?)
}

class ArticleViewHolder(
    private val mBinding: ItemHomeArticleLayoutBinding,
    private val onClick: ArticleAction.() -> Unit
) : BaseItemViewHolder(mBinding) {
    @SuppressLint("SetTextI18n")
    override fun onBind(data: UiModel?) {
        (data as? Article)?.let { article ->
            mBinding.apply {
                tvAuthor.text = article.getArticleAuthor()
                tvIsTop.isVisible = article.type == 1
                tvNew.isVisible = article.fresh
                tvTag1.text = article.tags[0].name
                tvTag2.text = article.tags[1].name
                tvDate.text = article.niceDate
                tvContent.text = Html.fromHtml(article.title)
                tvType2.text = "${article.superChapterName}.${article.chapterName}"
                ivCollect.setImageResource(if (article.collect) R.drawable.ic_collect else R.drawable.ic_uncollect)

                root.setOnClickListener {
                    onClick(ArticleAction.ItemClick(bindingAdapterPosition, article))
                }
                ivCollect.setOnClickListener {
                    onClick(ArticleAction.CollectClick(bindingAdapterPosition, article))
                }
                tvAuthor.setOnClickListener {
                    onClick(ArticleAction.AuthorClick(bindingAdapterPosition, article))
                }
            }
        }
    }
}