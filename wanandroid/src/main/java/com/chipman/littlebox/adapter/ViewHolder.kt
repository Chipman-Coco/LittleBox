package com.chipman.littlebox.adapter

import android.annotation.SuppressLint
import android.text.Html
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.chipman.common.util.GsonUtil
import com.chipman.domain.action.ArticleAction
import com.chipman.littlebox.wanandroid.R
import com.chipman.littlebox.wanandroid.databinding.ItemHomeArticleLayoutBinding
import com.chipman.littlebox.wanandroid.databinding.ItemHomeBannerLayoutBinding
import com.chipman.model.wanandroid.Article
import com.chipman.model.wanandroid.UiModel
import timber.log.Timber

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
            Timber.d("onBind: ${GsonUtil.toJson(article)}")
            mBinding.apply {
                tvAuthor.text = article.getArticleAuthor()
                tvIsTop.isVisible = article.type == 1
                tvNew.isVisible = article.fresh
                tvTag1.text = article.tags.elementAtOrNull(0)?.name
                tvTag2.text = article.tags.elementAtOrNull(1)?.name
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

class BannerViewHolder(
    private val mBinding: ItemHomeBannerLayoutBinding,
//    onClick: (Banner?, Int) -> Unit
) : BaseItemViewHolder(mBinding) {

    private val bannerAdapter = HomeBannerAdapter(emptyList()/*, onClick*/)

    override fun onBind(data: UiModel?) {
        Timber.d("onBind: $data")
//        (data as? Banners)?.let { data ->
//            with(mBinding.banner) {
//                setAdapter(bannerAdapter)
//                indicator = CircleIndicator(context)
//                addBannerLifecycleObserver(findViewTreeLifecycleOwner())
//            }
//            bannerAdapter.setDatas(data.banners)
//        }
    }
}