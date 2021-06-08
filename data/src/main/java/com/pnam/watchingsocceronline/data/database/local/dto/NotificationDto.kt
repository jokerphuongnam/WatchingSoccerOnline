package com.pnam.watchingsocceronline.data.database.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pnam.watchingsocceronline.domain.model.Notification

@Entity(tableName = "notifications")
data class NotificationDto(
    @PrimaryKey
    @ColumnInfo(name = "notification_id")
    var nid: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "thumbnail")
    var thumbnail: String,
    @ColumnInfo(name = "get_time")
    var getTime: Long,
    @ColumnInfo(name = "notification_time")
    var notificationTime: Long
) {
    constructor(nid: String) : this(nid, "", "", 0, 0)

    fun toNotification(): Notification {
        return Notification(nid, title, thumbnail, getTime, notificationTime)
    }
}