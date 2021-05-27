package com.pnam.watchingsocceronline.presentationphone.ui.main.container

import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemVideoHomeBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseListAdapter
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.VideosAdapter.VideoViewHolder
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import java.util.*

internal class VideosAdapter(
    private val itemCallback: ContainerItemCallback<Video>? = null,
    private val moreOptionClick: MoreOptionClick? = null
) : BaseListAdapter<Video, VideoViewHolder>(DIFF) {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder = VideoViewHolder(parent, itemCallback, moreOptionClick)

    internal class VideoViewHolder(
        parent: ViewGroup,
        itemCallback: ContainerItemCallback<Video>?,
        private val moreOptionClick: MoreOptionClick? = null
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
                moreOptionItem.setOnClickListener { moreOption ->
                    @MenuRes val menuRes: Int = if (data.showTime > Calendar.getInstance()
                            .apply { timeZone = TimeZone.getDefault() }.timeInMillis
                    ) {
                        R.menu.more_options_video_unplayed_menu
                    } else {
                        R.menu.more_options_video_show_menu
                    }
                    PopupMenu(moreOption.context, moreOption).apply {
                        inflate(menuRes)
                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.download -> {
                                    moreOptionClick?.download(data)
                                }
                                R.id.save_playlist -> {
                                    moreOptionClick?.saveToPlayList(data)
                                }
                                R.id.get_notification -> {
                                    moreOptionClick?.getNotification(data)
                                }
                            }
                            true
                        }
                        show()
                    }
                }
            }
        }
    }

    internal interface MoreOptionClick {
        fun download(video: Video)
        fun saveToPlayList(video: Video)
        fun getNotification(video: Video)
        fun removeFromHistory(video: Video)
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