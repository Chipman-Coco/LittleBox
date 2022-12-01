package com.chipman.littlebox.eyepetizer.ui.main.home.discover

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chipman.common.ktx.gone
import com.chipman.common.ktx.invisible
import com.chipman.common.ktx.visible
import com.chipman.littlebox.eyepetizer.holder.*
import com.chipman.model.eyepetizer.Discovery

class DiscoverAdapter : ListAdapter<Discovery.Item, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun getItemViewType(position: Int): Int = ViewHolderHelp.getItemViewType(getItem(position)!!)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolderHelp.getViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)!!
        when (holder) {
            is TextCardViewHeader5ViewHolder -> {
                holder.tvTitle5.text = item.data.text
                if (item.data.actionUrl != null) holder.ivInto5.visible() else holder.ivInto5.gone()
                if (item.data.follow != null) holder.tvFollow.visible() else holder.tvFollow.gone()
//                holder.tvFollow.setOnClickListener { LoginActivity.start(fragment.activity) }
//                setOnClickListener(holder.tvTitle5, holder.ivInto5) { ActionUrlUtil.process(fragment, item.data.actionUrl, item.data.text) }
            }
            is TextCardViewHeader7ViewHolder -> {
                holder.tvTitle7.text = item.data.text
                holder.tvRightText7.text = item.data.rightText
//                setOnClickListener(holder.tvRightText7, holder.ivInto7) {
//                    ActionUrlUtil.process(fragment, item.data.actionUrl, "${item.data.text},${item.data.rightText}")
//                }
            }
            is TextCardViewHeader8ViewHolder -> {
                holder.tvTitle8.text = item.data.text
                holder.tvRightText8.text = item.data.rightText
//                setOnClickListener(holder.tvRightText8, holder.ivInto8) { ActionUrlUtil.process(fragment, item.data.actionUrl, item.data.text) }
            }
            is TextCardViewFooter2ViewHolder -> {
                holder.tvFooterRightText2.text = item.data.text
//                setOnClickListener(holder.tvFooterRightText2, holder.ivTooterInto2) { ActionUrlUtil.process(fragment, item.data.actionUrl, item.data.text) }
            }
            is TextCardViewFooter3ViewHolder -> {
                holder.tvFooterRightText3.text = item.data.text
//                setOnClickListener(holder.tvRefresh, holder.tvFooterRightText3, holder.ivTooterInto3) {
//                    if (this == holder.tvRefresh) {
//                        "${holder.tvRefresh.text},${GlobalUtil.getString(R.string.currently_not_supported)}".showToast()
//                    } else if (this == holder.tvFooterRightText3 || this == holder.ivTooterInto3) {
//                        ActionUrlUtil.process(fragment, item.data.actionUrl, item.data.text)
//                    }
//                }
            }
            is FollowCardViewHolder -> {
//                holder.ivVideo.load(item.data.content.data.cover.feed, 4f)
//                holder.ivAvatar.load(item.data.header.icon)
//                holder.tvVideoDuration.text = item.data.content.data.duration.conversionVideoDuration()
                holder.tvDescription.text = item.data.header.description
                holder.tvTitle.text = item.data.header.title
                if (item.data.content.data.ad) holder.tvLabel.visible() else holder.tvLabel.gone()
//                if (item.data.content.data.library == DailyAdapter.DAILY_LIBRARY_TYPE) holder.ivChoiceness.visible() else holder.ivChoiceness.gone()
//                holder.ivShare.setOnClickListener { showDialogShare(fragment.activity, "${item.data.content.data.title}：${item.data.content.data.webUrl.raw}") }
//                holder.itemView.setOnClickListener {
//                    item.data.content.data.run {
//                        if (ad || author == null) {
//                            NewDetailActivity.start(fragment.activity, id)
//                        } else {
//                            NewDetailActivity.start(
//                                fragment.activity, NewDetailActivity.VideoInfo(id, playUrl, title, description, category, library, consumption, cover, author, webUrl)
//                            )
//                        }
//                    }
//                }
            }
            is HorizontalScrollCardViewHolder -> {
//                holder.bannerViewPager.run {
//                    setCanLoop(false)
//                    setRoundCorner(dp2px(4f))
//                    setRevealWidth(GlobalUtil.getDimension(R.dimen.listSpaceSize))
//                    if (item.data.itemList.size == 1) setPageMargin(0) else setPageMargin(dp2px(4f))
//                    setIndicatorVisibility(View.GONE)
//                    setAdapter(HorizontalScrollCardAdapter())
//                    removeDefaultPageTransformer()
//                    setOnPageClickListener { position ->
//                        ActionUrlUtil.process(fragment, item.data.itemList[position].data.actionUrl, item.data.itemList[position].data.title)
//                    }
//                    create(item.data.itemList)
//                }
            }
            is SpecialSquareCardCollectionViewHolder -> {
                holder.tvTitle.text = item.data.header.title
                holder.tvRightText.text = item.data.header.rightText
//                setOnClickListener(
//                    holder.tvRightText,
//                    holder.ivInto
//                ) { "${item.data.header.rightText},${GlobalUtil.getString(R.string.currently_not_supported)}".showToast() }
                holder.recyclerView.setHasFixedSize(true)
                holder.recyclerView.isNestedScrollingEnabled = true
//                holder.recyclerView.layoutManager = GridLayoutManager(fragment.activity, 2).apply { orientation = GridLayoutManager.HORIZONTAL }
//                if (holder.recyclerView.itemDecorationCount == 0) {
//                    holder.recyclerView.addItemDecoration(SpecialSquareCardCollectionItemDecoration())
//                }
//                val list = item.data.itemList.filter { it.type == "squareCardOfCategory" && it.data.dataType == "SquareCard" }
//                holder.recyclerView.adapter = SpecialSquareCardCollectionAdapter(list)
            }
            is ColumnCardListViewHolder -> {
                holder.tvTitle.text = item.data.header.title
                holder.tvRightText.text = item.data.header.rightText
//                setOnClickListener(
//                    holder.tvRightText,
//                    holder.ivInto
//                ) { "${item.data.header.rightText},${GlobalUtil.getString(R.string.currently_not_supported)}".showToast() }
                holder.recyclerView.setHasFixedSize(true)
//                holder.recyclerView.layoutManager = GridLayoutManager(fragment.activity, 2)
//                if (holder.recyclerView.itemDecorationCount == 0) {
//                    holder.recyclerView.addItemDecoration(GridListItemDecoration(2))
//                }
                val list = item.data.itemList.filter { it.type == "squareCardOfColumn" && it.data.dataType == "SquareCard" }
//                holder.recyclerView.adapter = ColumnCardListAdapter(list)
            }
            is BannerViewHolder -> {
//                holder.ivPicture.load(item.data.image, 4f)
//                holder.itemView.setOnClickListener { ActionUrlUtil.process(fragment, item.data.actionUrl, item.data.title) }
            }
            is Banner3ViewHolder -> {
//                holder.ivPicture.load(item.data.image, 4f)
//                holder.ivAvatar.load(item.data.header.icon)
                holder.tvTitle.text = item.data.header.title
                holder.tvDescription.text = item.data.header.description
                if (item.data.label?.text.isNullOrEmpty()) holder.tvLabel.invisible() else holder.tvLabel.visible()
                holder.tvLabel.text = item.data.label?.text ?: ""
//                holder.itemView.setOnClickListener { ActionUrlUtil.process(fragment, item.data.actionUrl, item.data.header.title) }
            }
            is VideoSmallCardViewHolder -> {
//                holder.ivPicture.load(item.data.cover.feed, 4f)
//                holder.tvDescription.text = if (item.data.library == DailyAdapter.DAILY_LIBRARY_TYPE) "#${item.data.category} / 开眼精选" else "#${item.data.category}"
//                holder.tvTitle.text = item.data.title
//                holder.tvVideoDuration.text = item.data.duration.conversionVideoDuration()
//                holder.ivShare.setOnClickListener { showDialogShare(fragment.activity, "${item.data.title}：${item.data.webUrl.raw}") }
//                holder.itemView.setOnClickListener {
//                    item.data.run {
//                        NewDetailActivity.start(
//                            fragment.activity, NewDetailActivity.VideoInfo(id, playUrl, title, description, category, library, consumption, cover, author, webUrl)
//                        )
//                    }
//                }
            }
            is TagBriefCardViewHolder -> {
//                holder.ivPicture.load(item.data.icon, 4f)
                holder.tvDescription.text = item.data.description
                holder.tvTitle.text = item.data.title
                if (item.data.follow != null) holder.tvFollow.visible() else holder.tvFollow.gone()
//                holder.tvFollow.setOnClickListener { LoginActivity.start(fragment.activity) }
//                holder.itemView.setOnClickListener { "${item.data.title},${GlobalUtil.getString(R.string.currently_not_supported)}".showToast() }
            }
            is TopicBriefCardViewHolder -> {
//                holder.ivPicture.load(item.data.icon, 4f)
                holder.tvDescription.text = item.data.description
                holder.tvTitle.text = item.data.title
//                holder.itemView.setOnClickListener { "${item.data.title},${GlobalUtil.getString(R.string.currently_not_supported)}".showToast() }
            }
//            is AutoPlayVideoAdViewHolder -> {
//                item.data.detail?.run {
//                    holder.ivAvatar.load(item.data.detail.icon)
//                    holder.tvTitle.text = item.data.detail.title
//                    holder.tvDescription.text = item.data.detail.description
//                    CommendAdapter.startAutoPlay(fragment.activity, holder.videoPlayer, position, url, imageUrl, TAG, object : GSYSampleCallBack() {
//                        override fun onPrepared(url: String?, vararg objects: Any?) {
//                            super.onPrepared(url, *objects)
//                            GSYVideoManager.instance().isNeedMute = true
//                        }
//
//                        override fun onClickBlank(url: String?, vararg objects: Any?) {
//                            super.onClickBlank(url, *objects)
//                            ActionUrlUtil.process(fragment, item.data.detail.actionUrl)
//                        }
//                    })
//                    setOnClickListener(holder.videoPlayer.thumbImageView, holder.itemView) {
//                        ActionUrlUtil.process(fragment, item.data.detail.actionUrl)
//                    }
//                }
//            }
            else -> {
                holder.itemView.gone()
//                if (BuildConfig.DEBUG) "${TAG}:${Const.Toast.BIND_VIEWHOLDER_TYPE_WARN}\n${holder}".showToast()
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Discovery.Item>() {
            override fun areItemsTheSame(oldItem: Discovery.Item, newItem: Discovery.Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Discovery.Item, newItem: Discovery.Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}