package com.pnam.watchingsocceronline.data.database.local

import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.Video

interface NotificationLocal {
    suspend fun getNotifications(): List<Notification>
    suspend fun getNotification(video: Video)
    suspend fun deleteNotification(notification: Notification)
}