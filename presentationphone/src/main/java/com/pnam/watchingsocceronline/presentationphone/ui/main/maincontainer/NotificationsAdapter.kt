package com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemNotificaionBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseListAdapter
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

internal class NotificationsAdapter(
    private val itemCallback: ContainerItemCallback<Notification>? = null,
) : BaseListAdapter<Notification, NotificationsAdapter.NotificationViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(parent, itemCallback)

    internal class NotificationViewHolder(
        parent: ViewGroup,
        itemCallback: ContainerItemCallback<Notification>?
    ) :
        BaseListAdapter.BaseViewHolder<ItemNotificaionBinding, Notification>(
            parent,
            R.layout.item_notificaion,
            itemCallback
        ) {
        override fun onBind(data: Notification) {
            binding.apply {
                notification = data
                container.setOnClickListener { itemCallback?.onClick(data) }
            }
        }
    }

    private companion object {
        private val DIFF: DiffUtil.ItemCallback<Notification> by lazy {
            object : DiffUtil.ItemCallback<Notification>() {
                override fun areItemsTheSame(
                    oldItem: Notification,
                    newItem: Notification
                ): Boolean {
                    return oldItem.nid == newItem.nid
                }

                override fun areContentsTheSame(
                    oldItem: Notification,
                    newItem: Notification
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
        }
    }
}