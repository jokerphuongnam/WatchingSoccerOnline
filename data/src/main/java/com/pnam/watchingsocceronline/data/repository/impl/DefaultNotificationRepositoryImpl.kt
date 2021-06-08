package com.pnam.watchingsocceronline.data.repository.impl

import com.pnam.watchingsocceronline.data.database.local.NotificationLocal
import com.pnam.watchingsocceronline.data.repository.NotificationRepository
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Inject

class DefaultNotificationRepositoryImpl @Inject constructor(
    override val notificationLocal: NotificationLocal
) : NotificationRepository {
    override suspend fun getNotifications(): List<Notification> {
        return notificationLocal.getNotifications()
    }

    override suspend fun getNotification(video: Video) {
        notificationLocal.getNotification(video)
    }

    override suspend fun deleteNotification(notification: Notification) {
        notificationLocal.deleteNotification(notification)
    }
}