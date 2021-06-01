package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.model.model.Notification
import com.pnam.watchingsocceronline.model.model.SearchHistory
import com.pnam.watchingsocceronline.model.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface MainContainerUseCase {
    fun getSearchHistory(searchWord: String? = null): Flow<MutableList<SearchHistory>>
    suspend fun getSearchResult(searchWord: String): MutableList<Video>
    suspend fun getNotifications(): MutableList<Notification>
    suspend fun getNotification(video: Video): Notification
    suspend fun downloadVideo(video: Video): Long
}