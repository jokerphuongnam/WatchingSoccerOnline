package com.pnam.watchingsocceronline.data.di

import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.database.local.impl.DataStoreCurrentUserImpl
import com.pnam.watchingsocceronline.data.database.local.impl.DownloadManagerDownloadVideoImpl
import com.pnam.watchingsocceronline.data.database.network.SearchNetwork
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.database.network.impl.FakeSearchNetworkImpl
import com.pnam.watchingsocceronline.data.database.network.impl.FakeVideoNetworkImpl
import com.pnam.watchingsocceronline.data.database.network.impl.RetrofitUserNetworkImpl
import com.pnam.watchingsocceronline.data.repository.*
import com.pnam.watchingsocceronline.data.repository.impl.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindsModules {
    @Binds
    abstract fun getDownloadNetwork(videoNetwork: FakeVideoNetworkImpl): VideoNetwork

    @Binds
    abstract fun getSearchNetwork(searchNetwork: FakeSearchNetworkImpl): SearchNetwork

    @Binds
    abstract fun getUserNetwork(userNetwork: RetrofitUserNetworkImpl): UserNetwork

    @Binds
    abstract fun getDownloadVideo(downloadVideo: DownloadManagerDownloadVideoImpl): DownloadVideo

    @Binds
    abstract fun getCurrentUser(currentUser: DataStoreCurrentUserImpl): CurrentUser

    @Binds
    abstract fun getSearchRepository(repository: DefaultSearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun getDownloadRepository(repository: DefaultVideoRepositoryImpl): VideoRepository

    @Binds
    abstract fun getUserRepository(repository: DefaultUserRepositoryImpl): UserRepository

    @Binds
    abstract fun getCommentRepository(repository: DefaultCommentsRepositoryImpl): CommentsRepository

    @Binds
    abstract fun getNotificationRepository(repository: DefaultNotificationRepositoryImpl): NotificationRepository
}