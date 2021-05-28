package com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.pnam.watchingsocceronline.model.model.SearchHistory
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemSearchBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseListAdapter
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

internal class SearchAdapter(
    private val itemCallback: ContainerItemCallback<SearchHistory>? = null,
) : BaseListAdapter<SearchHistory, SearchAdapter.SearchViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(parent, itemCallback)

    internal class SearchViewHolder(
        parent: ViewGroup,
        itemCallback: ContainerItemCallback<SearchHistory>?
    ) :
        BaseListAdapter.BaseViewHolder<ItemSearchBinding, SearchHistory>(
            parent,
            R.layout.item_search,
            itemCallback
        ) {
        override fun onBind(data: SearchHistory) {
            binding.apply {
                searchHistory = data
                container.setOnClickListener { itemCallback?.onClick(data) }
            }
        }
    }

    private companion object {
        private val DIFF: DiffUtil.ItemCallback<SearchHistory> by lazy {
            object : DiffUtil.ItemCallback<SearchHistory>() {
                override fun areItemsTheSame(
                    oldItem: SearchHistory,
                    newItem: SearchHistory
                ): Boolean {
                    return oldItem.sid == newItem.sid
                }

                override fun areContentsTheSame(
                    oldItem: SearchHistory,
                    newItem: SearchHistory
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
        }
    }
}