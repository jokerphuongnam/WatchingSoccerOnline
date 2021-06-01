package com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemVideoHomeBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseListAdapter
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

class RecommendAdapter(
    private val itemVideoCallback: ContainerItemCallback<Video>? = null
) : BaseListAdapter<Video, RecommendAdapter.RecommendViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder =
        RecommendViewHolder(parent, R.layout.item_video_home, itemVideoCallback)

    class RecommendViewHolder(
        parent: ViewGroup,
        layout: Int,
        itemCallback: ContainerItemCallback<Video>? = null
    ) : BaseViewHolder<ItemVideoHomeBinding, Video>(parent, layout, itemCallback) {
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