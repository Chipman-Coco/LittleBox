package com.chipman.littlebox.eyepetizer.holder

import android.view.View
import android.view.ViewGroup
import com.chipman.littlebox.eyepetizer.R
import com.chipman.littlebox.eyepetizer.constant.ItemType
import com.chipman.littlebox.eyepetizer.ktx.inflate
import com.chipman.model.eyepetizer.*

object ViewHolderHelp {

    fun getViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {

        ItemType.TEXT_CARD_HEADER4 -> TextCardViewHeader4ViewHolder(R.layout.item_text_card_type_header_four.inflate(parent))

        ItemType.TEXT_CARD_HEADER5 -> TextCardViewHeader5ViewHolder(R.layout.item_text_card_type_header_five.inflate(parent))

        ItemType.TEXT_CARD_HEADER7 -> TextCardViewHeader7ViewHolder(R.layout.item_text_card_type_header_seven.inflate(parent))

        ItemType.TEXT_CARD_HEADER8 -> TextCardViewHeader8ViewHolder(R.layout.item_text_card_type_header_eight.inflate(parent))

        ItemType.TEXT_CARD_FOOTER2 -> TextCardViewFooter2ViewHolder(R.layout.item_text_card_type_footer_two.inflate(parent))

        ItemType.TEXT_CARD_FOOTER3 -> TextCardViewFooter3ViewHolder(R.layout.item_text_card_type_footer_three.inflate(parent))

        ItemType.HORIZONTAL_SCROLL_CARD -> HorizontalScrollCardViewHolder(R.layout.item_horizontal_scroll_card_type.inflate(parent))

        ItemType.SPECIAL_SQUARE_CARD_COLLECTION -> SpecialSquareCardCollectionViewHolder(R.layout.item_special_square_card_collection_type.inflate(parent))

        ItemType.COLUMN_CARD_LIST -> ColumnCardListViewHolder(R.layout.item_column_card_list_type.inflate(parent))

        ItemType.BANNER -> BannerViewHolder(R.layout.item_banner_type.inflate(parent))

        ItemType.BANNER3 -> Banner3ViewHolder(R.layout.item_banner_three_type.inflate(parent))

//        ItemType.VIDEO_SMALL_CARD -> VideoSmallCardViewHolder(R.layout.item_video_small_card_type.inflate(parent))

        ItemType.TAG_BRIEFCARD -> TagBriefCardViewHolder(R.layout.item_brief_card_tag_brief_card_type.inflate(parent))

        ItemType.TOPIC_BRIEFCARD -> TopicBriefCardViewHolder(R.layout.item_brief_card_topic_brief_card_type.inflate(parent))

        ItemType.FOLLOW_CARD -> FollowCardViewHolder(R.layout.item_follow_card_type.inflate(parent))

        ItemType.INFORMATION_CARD -> InformationCardFollowCardViewHolder(R.layout.item_information_card_type.inflate(parent))

//        ItemType.UGC_SELECTED_CARD_COLLECTION -> UgcSelectedCardCollectionViewHolder(R.layout.item_ugc_selected_card_collection_type.inflate(parent))

//        ItemType.AUTO_PLAY_VIDEO_AD -> AutoPlayVideoAdViewHolder(R.layout.item_auto_play_video_ad.inflate(parent))

        else -> EmptyViewHolder(View(parent.context))
    }

    fun getItemViewType(type: String, dataType: String): Int {
        return when (type) {
            "horizontalScrollCard" -> {
                when(dataType) {
                    "HorizontalScrollCard" -> ItemType.HORIZONTAL_SCROLL_CARD
                    else -> ItemType.UNKNOWN
                }
            }
            "specialSquareCardCollection" -> {
                when (dataType) {
                    "ItemCollection" -> ItemType.SPECIAL_SQUARE_CARD_COLLECTION
                    else -> ItemType.UNKNOWN
                }
            }
            "columnCardList" -> {
                when (dataType) {
                    "ItemCollection" -> ItemType.COLUMN_CARD_LIST
                    else -> ItemType.UNKNOWN
                }
            }
            "banner" -> {
                when (dataType) {
                    "Banner" -> ItemType.BANNER
                    else -> ItemType.UNKNOWN
                }
            }
            "banner3" -> {
                when (dataType) {
                    "Banner" -> ItemType.BANNER3
                    else -> ItemType.UNKNOWN
                }
            }
            "videoSmallCard" -> {
                when (dataType) {
                    "VideoBeanForClient" -> ItemType.VIDEO_SMALL_CARD
                    else -> ItemType.UNKNOWN
                }
            }
            "briefCard" -> {
                when (dataType) {
                    "TagBriefCard" -> ItemType.TAG_BRIEFCARD
                    "TopicBriefCard" -> ItemType.TOPIC_BRIEFCARD
                    else -> ItemType.UNKNOWN
                }
            }
            "followCard" -> {
                when (dataType) {
                    "FollowCard" -> ItemType.FOLLOW_CARD
                    else -> ItemType.UNKNOWN
                }
            }
            "informationCard" -> {
                when (dataType) {
                    "InformationCard" -> ItemType.INFORMATION_CARD
                    else -> ItemType.UNKNOWN
                }
            }
            "ugcSelectedCardCollection" -> {
                when (dataType) {
                    "ItemCollection" -> ItemType.UGC_SELECTED_CARD_COLLECTION
                    else -> ItemType.UNKNOWN
                }
            }
            "autoPlayVideoAd" -> {
                when (dataType) {
                    "AutoPlayVideoAdDetail" -> ItemType.AUTO_PLAY_VIDEO_AD
                    else -> ItemType.UNKNOWN
                }
            }
            else -> ItemType.UNKNOWN
        }
    }

    private fun getTextCardType(type: String) = when (type) {
        "header4" -> ItemType.TEXT_CARD_HEADER4
        "header5" -> ItemType.TEXT_CARD_HEADER5
        "header7" -> ItemType.TEXT_CARD_HEADER7
        "header8" -> ItemType.TEXT_CARD_HEADER8
        "footer2" -> ItemType.TEXT_CARD_FOOTER2
        "footer3" -> ItemType.TEXT_CARD_FOOTER3
        else -> ItemType.UNKNOWN
    }

    fun getItemViewType(item: Discovery.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: HomePageRecommend.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: Daily.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: Follow.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: VideoRelated.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }

    fun getItemViewType(item: VideoReplies.Item): Int {
        return if (item.type == "textCard") getTextCardType(item.data.type) else getItemViewType(item.type, item.data.dataType)
    }
}