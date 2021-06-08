package com.pnam.watchingsocceronline.data.database.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pnam.watchingsocceronline.data.database.local.dto.DownloadDto
import com.pnam.watchingsocceronline.data.database.local.dto.NotificationDto
import com.pnam.watchingsocceronline.data.database.local.dto.UserDto
import com.pnam.watchingsocceronline.data.database.local.impl.DownloadDao
import com.pnam.watchingsocceronline.data.database.local.impl.NotificationDao
import com.pnam.watchingsocceronline.data.database.local.impl.UserDao
import com.pnam.watchingsocceronline.data.utils.RoomUtils
import javax.inject.Singleton

@Singleton
@Database(
    entities = [DownloadDto::class, UserDto::class, NotificationDto::class],
    version = RoomUtils.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDownloadDao(): DownloadDao
    abstract fun getUserDao(): UserDao
    abstract fun getNotificationDao(): NotificationDao
}