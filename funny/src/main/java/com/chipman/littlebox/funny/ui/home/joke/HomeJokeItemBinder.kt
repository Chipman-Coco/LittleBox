package com.chipman.littlebox.funny.ui.home.joke

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chipman.littlebox.funny.R
import com.chipman.littlebox.funny.databinding.ItemJokeCommonLayoutBinding
import com.chipman.littlebox.funny.service.model.JokeInfo
import com.chipman.littlebox.funny.service.model.RecommendInfo
import com.chipman.littlebox.funny.service.model.User
import com.chipman.littlebox.funny.util.AESUtil
import com.chipman.multitype.PagingItemViewBinder
import com.hjq.toast.ToastUtils
import timber.log.Timber

/**
 * [ViewBinding] ViewHolder
 */
open class ViewBindingHolder<VB : ViewBinding>(open val binding: VB) :
    RecyclerView.ViewHolder(binding.root)

/**
 * joke item
 */
class HomeJokeItemBinder(
    private val onClick: (JokeAction) -> Unit
) : PagingItemViewBinder<RecommendInfo, ViewBindingHolder<ItemJokeCommonLayoutBinding>>() {

    override fun onBindViewHolder(holder: ViewBindingHolder<ItemJokeCommonLayoutBinding>, item: RecommendInfo) {
        holder.binding.apply {
            user.also {
                Glide.with(it.avatar.context)
                    .load(item.user.avatar)
                    .fitCenter()
                    .transform(CircleCrop())
                    .into(it.avatar)
                it.nickname.text = item.user.nickName
                it.signature.text = item.user.signature
                it.attention.apply {
                    if (item.info.isAttention) {
                        text = "已关注"
                        setTextColor(context.getColor(R.color.material_grey_300))
                    } else {
                        text = "+关注"
                        setTextColor(context.getColor(R.color.material_deep_orange_700))
                    }
                    setOnClickListener {
                        onClick(JokeAction.AttentionAction(holder.bindingAdapterPosition, item.user))
                    }
                }
                it.plugin.setOnClickListener {
                    ToastUtils.show("没有更多~")
                }
                loadContent(holder, item)
            }
            social.also {
                it.like.text = item.info.likeNum.toString()
                it.like.setTextColor(
                    if (item.info.isLike) ContextCompat.getColor(
                        root.context,
                        R.color.material_yellow_300
                    ) else ContextCompat.getColor(root.context, R.color.black)
                )
                it.unlike.text = item.info.disLikeNum.toString()
                it.unlike.setTextColor(
                    if (item.info.isUnlike) ContextCompat.getColor(
                        root.context,
                        R.color.material_yellow_300
                    ) else ContextCompat.getColor(root.context, R.color.black)
                )
                it.message.text = item.info.commentNum.toString()
                it.share.text = item.info.shareNum.toString()
            }
        }
    }

    private fun loadContent(holder: ViewBindingHolder<ItemJokeCommonLayoutBinding>, item: RecommendInfo) {
        holder.binding.apply {
            user.content.text = item.joke.content
            when (item.joke.type) {
                1 -> {
                    user.image.also {
                        it.isVisible = false
                        it.setImageDrawable(null)
                    }
                }
                2 -> {
                    user.image.also {
                        it.isVisible = true
                        val decryptResult = AESUtil.decrypt(item.joke.imageUrl)
                        val imageArray = decryptResult.replace(",", "")
                        Timber.d("decryptResult: $decryptResult，imageArray: $imageArray")
//                        it.tag = decryptResult
                        Glide.with(it.context)
                            .load(decryptResult/*imageArray.last()*/)
                            .transform(RoundedCorners(10))
                            .into(it)
                    }
                }
                else -> {
                    user.image.also {
                        it.isVisible = false
                        it.setImageDrawable(null)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewBindingHolder<ItemJokeCommonLayoutBinding> {
        return ViewBindingHolder(
            ItemJokeCommonLayoutBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }
}

sealed interface JokeAction {

    /**
     * 点击关注
     */
    data class AttentionAction(val position: Int, val user: User) : JokeAction

    /**
     * 点赞
     */
    data class LikeAction(val position: Int, val joke: JokeInfo) : JokeAction

    /**
     * 点踩
     */
    data class UnLikeAction(val position: Int, val joke: JokeInfo) : JokeAction
}