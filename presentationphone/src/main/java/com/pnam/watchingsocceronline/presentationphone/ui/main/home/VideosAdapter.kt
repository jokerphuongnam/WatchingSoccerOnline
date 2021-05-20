package com.pnam.watchingsocceronline.presentationphone.ui.main.home

import android.os.Parcelable
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.pnam.watchingsocceronline.model.model.SearchHistory
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewHolder
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemSearchBinding
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemVideoHomeBinding
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import com.pnam.watchingsocceronline.presentationphone.utils.RecyclerType

class VideosAdapter(
    private val itemSearchCallback: ContainerItemCallback<SearchHistory>,
    private val itemVideoCallback: ContainerItemCallback<Video>
) :
    ListAdapter<Parcelable, BaseViewHolder<ViewDataBinding, Parcelable>>(DIFF) {

    override fun submitList(list: MutableList<Parcelable>?) {
        super.submitList(list)
        list?.takeIf { it.isNotEmpty() }?.let {
            recyclerType = when (it[0]){
                is Video -> RecyclerType.OTHER
                is SearchHistory -> RecyclerType.SEARCH
                else -> RecyclerType.OTHER
            }
        }
    }

    var recyclerType: RecyclerType = RecyclerType.OTHER

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding, Parcelable> = when (recyclerType) {
        RecyclerType.SEARCH -> SearchViewHolder(parent, itemSearchCallback)
        RecyclerType.OTHER -> VideoHomeViewHolder(parent, itemVideoCallback)
    } as BaseViewHolder<ViewDataBinding, Parcelable>


    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding, Parcelable>,
        position: Int
    ) {
        holder.onBind(getItem(position))
    }

    private class SearchViewHolder(
        parent: ViewGroup,
        private val itemCallback: ContainerItemCallback<SearchHistory>
    ) :
        BaseViewHolder<ItemSearchBinding, SearchHistory>(
            parent,
            R.layout.item_search,
            itemCallback
        ) {
        override fun onBind(data: SearchHistory) {
            binding.apply {
                searchHistory = data
                container.setOnClickListener { itemCallback.onClick(data) }
            }
        }
    }

    private class VideoHomeViewHolder(
        parent: ViewGroup,
        private val itemCallback: ContainerItemCallback<Video>
    ) :
        BaseViewHolder<ItemVideoHomeBinding, Video>(
            parent,
            R.layout.item_video_home,
            itemCallback
        ) {
        override fun onBind(data: Video) {
            binding.apply {
                video = data
                container.setOnClickListener { itemCallback.onClick(data) }
            }
        }
    }

    private companion object {
        private val DIFF: DiffUtil.ItemCallback<Parcelable> by lazy {
            object : DiffUtil.ItemCallback<Parcelable>() {
                override fun areItemsTheSame(oldItem: Parcelable, newItem: Parcelable): Boolean {
                    return oldItem.describeContents() == newItem.describeContents()
                }

                override fun areContentsTheSame(oldItem: Parcelable, newItem: Parcelable): Boolean {
                    return oldItem.equals(newItem)
                }
            }
        }
    }
}