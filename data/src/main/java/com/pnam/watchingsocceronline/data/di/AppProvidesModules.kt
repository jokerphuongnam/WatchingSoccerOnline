package com.pnam.watchingsocceronline.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.pnam.watchingsocceronline.data.database.local.AppDatabase
import com.pnam.watchingsocceronline.data.database.local.DownloadLocal
import com.pnam.watchingsocceronline.data.database.local.UserLocal
import com.pnam.watchingsocceronline.data.utils.RoomUtils.DB_NAME
import com.pnam.watchingsocceronline.data.utils.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppProvidesModules {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideDownloadLocal(database: AppDatabase): DownloadLocal = database.getDownloadDao()

    @Provides
    @Singleton
    fun provideUserLocal(database: AppDatabase): UserLocal = database.getUserDao()
}