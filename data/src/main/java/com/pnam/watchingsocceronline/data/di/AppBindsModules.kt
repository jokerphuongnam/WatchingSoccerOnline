package com.pnam.watchingsocceronline.data.di

import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.database.local.impl.DataStoreCurrentUserImpl
import com.pnam.watchingsocceronline.data.database.local.impl.DownloadManagerDownloadVideoImpl
import com.pnam.watchingsocceronline.data.database.network.SearchNetwork
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.database.network.impl.FakeSearchNetworkImpl
import com.pnam.watchingsocceronline.data.database.network.impl.FakeUserNetworkImpl
import com.pnam.watchingsocceronline.data.database.network.impl.FakeVideoNetworkImpl
import com.pnam.watchingsocceronline.data.repository.CommentsRepository
import com.pnam.watchingsocceronline.data.repository.SearchRepository
import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.data.repository.impl.DefaultCommentsRepositoryImpl
import com.pnam.watchingsocceronline.data.repository.impl.DefaultSearchRepositoryImpl
import com.pnam.watchingsocceronline.data.repository.impl.DefaultUserRepositoryImpl
import com.pnam.watchingsocceronline.data.repository.impl.DefaultVideoRepositoryImpl
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
    abstract fun getDownloadNetwork(videoNetwork: FakeVideoNetworkImpl): VideoNetwork

    @Binds
    abstract fun getSearchNetwork(searchNetwork: FakeSearchNetworkImpl): SearchNetwork

    @Binds
    abstract fun getSearchRepository(repository: DefaultSearchRepositoryImpl): SearchRepository

    @Binds
    abstract fun getUserNetwork(userNetwork: FakeUserNetworkImpl): UserNetwork

    @Binds
    abstract fun getCurrentUser(currentUser: DataStoreCurrentUserImpl): CurrentUser

    @Binds
    abstract fun getDownloadRepository(repository: DefaultVideoRepositoryImpl): VideoRepository

    @Binds
    abstract fun getUserRepository(repository: DefaultUserRepositoryImpl): UserRepository

    @Binds
    abstract fun getCommentRepository(repository: DefaultCommentsRepositoryImpl): CommentsRepository
}