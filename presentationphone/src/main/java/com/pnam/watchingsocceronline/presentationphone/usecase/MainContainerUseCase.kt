package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.NotificationRepository
import com.pnam.watchingsocceronline.data.repository.SearchRepository
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface MainContainerUseCase {
    val videoRepository: VideoRepository
    val searchRepository: SearchRepository
    val notificationRepository: NotificationRepository
    fun getSearchHistory(searchWord: String? = null): Flow<MutableList<SearchHistory>>
    suspend fun getSearchResult(searchWord: String): List<Video>
    suspend fun getNotifications(): List<Notification>
    suspend fun getNotification(video: Video)
    suspend fun removeNotification(notification: Notification)
    suspend fun saveVideoDownload(video: Download)
    suspend fun getVideoDownload(video: Video): Download
}