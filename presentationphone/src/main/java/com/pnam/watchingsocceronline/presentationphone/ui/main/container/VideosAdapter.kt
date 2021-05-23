package com.pnam.watchingsocceronline.presentationphone.ui.main.container

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemVideoHomeBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseListAdapter
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.VideosAdapter.VideoViewHolder
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

internal class VideosAdapter(
    private val itemCallback: ContainerItemCallback<Video>? = null,
) : BaseListAdapter<Video, VideoViewHolder>(DIFF) {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder = VideoViewHolder(parent, itemCallback)

    internal class VideoViewHolder(
        parent: ViewGroup,
        itemCallback: ContainerItemCallback<Video>?
    ) :
        BaseViewHolder<ItemVideoHomeBinding, Video>(
            parent,
            R.layout.item_video_home,
            itemCallback
        ) {
        override fun onBind(data: Video) {
            binding.apply {
                video = data
                container.setOnClickListener { itemCallback?.onClick(data) }
            }
        }
    }

    private companion object {
        private val DIFF: DiffUtil.ItemCallback<Video> by lazy {
            object : DiffUtil.ItemCallback<Video>() {
                override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
                    return oldItem.vid == newItem.vid
                }

                override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
                    return oldItem.equals(newItem)
                }
            }
        }
    }
}