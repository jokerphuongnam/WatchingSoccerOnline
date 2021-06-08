package com.pnam.watchingsocceronline.data.database.local.impl

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.pnam.watchingsocceronline.data.database.local.NotificationLocal
import com.pnam.watchingsocceronline.data.database.local.dto.NotificationDto
import com.pnam.watchingsocceronline.data.utils.toDto
import com.pnam.watchingsocceronline.data.utils.toNotificationDto
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.Video

@Dao
interface NotificationDao : NotificationLocal {

    @Query("SELECT * FROM NOTIFICATIONS")
    suspend fun getNotificationsDto(): List<NotificationDto>

    override suspend fun getNotifications(): List<Notification> {
        return getNotificationsDto().map { it.toNotification() }
    }

    @Insert(onConflict = REPLACE)
    suspend fun insertNotification(notificationDto: NotificationDto)

    override suspend fun getNotification(video: Video) {
        insertNotification(video.toNotificationDto())
    }

    @Delete
    suspend fun deleteNotificationDto(notificationDto: NotificationDto)

    override suspend fun deleteNotification(notification: Notification) {
        deleteNotificationDto(notification.toDto())
    }
}