package com.pnam.watchingsocceronline.data.di

import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.database.local.impl.DownloadManagerDownloadVideoImpl
import com.pnam.watchingsocceronline.data.repository.DownloadRepository
import com.pnam.watchingsocceronline.data.repository.impl.DefaultDownloadRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModules {
    @Binds
    abstract fun getDownloadVideo(downloadVideo: DownloadManagerDownloadVideoImpl): DownloadVideo

    @Binds
    abstract fun getDownloadRepository(repository: DefaultDownloadRepositoryImpl): DownloadRepository
}