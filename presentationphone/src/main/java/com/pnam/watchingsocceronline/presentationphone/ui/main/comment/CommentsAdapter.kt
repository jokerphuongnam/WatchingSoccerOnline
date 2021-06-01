package com.pnam.watchingsocceronline.presentationphone.ui.main.comment

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemCommentBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseListAdapter


class CommentsAdapter : BaseListAdapter<Comment, CommentsAdapter.CommentViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder =
        CommentViewHolder(parent)

    class CommentViewHolder(
        parent: ViewGroup
    ) : BaseViewHolder<ItemCommentBinding, Comment>(
        parent,
        R.layout.item_comment
    ) {
        override fun onBind(data: Comment) {
            binding.comment = data
        }
    }

    companion object {
        private val DIFF: DiffUtil.ItemCallback<Comment> by lazy {
            object : DiffUtil.ItemCallback<Comment>() {
                override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
                    oldItem.cid.equals(newItem.cid)

                override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
                    oldItem.equals(newItem)
            }
        }
    }
}