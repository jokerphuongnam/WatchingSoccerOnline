package com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer

import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ItemNotificaionBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseListAdapter
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

internal class NotificationsAdapter(
    private val itemCallback: ContainerItemCallback<Notification>? = null,
    private val moreOptions: MoreOptionsNotification? = null
) : BaseListAdapter<Notification, NotificationsAdapter.NotificationViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder =
        NotificationViewHolder(parent, itemCallback, moreOptions)

    internal class NotificationViewHolder(
        parent: ViewGroup,
        itemCallback: ContainerItemCallback<Notification>?,
        private val moreOptions: MoreOptionsNotification? = null
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
                container.setOnLongClickListener {
                    PopupMenu(container.context, container).apply {
                        inflate(R.menu.more_options_notification_menu)
                        setOnMenuItemClickListener { menuItem ->
                            when (menuItem.itemId) {
                                R.id.remove_notification -> {
                                    moreOptions?.removeNotification(data)
                                }
                            }
                            true
                        }
                        show()
                    }
                    true
                }
            }
        }
    }

    interface MoreOptionsNotification{
        fun removeNotification(data: Notification)
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