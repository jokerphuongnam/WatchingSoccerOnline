package com.pnam.watchingsocceronline.data.repository

import com.pnam.watchingsocceronline.data.database.local.NotificationLocal
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface NotificationRepository {
    val notificationLocal: NotificationLocal

    suspend fun getNotifications(): List<Notification>
    suspend fun getNotification(video: Video)
    suspend fun deleteNotification(notification: Notification)
}