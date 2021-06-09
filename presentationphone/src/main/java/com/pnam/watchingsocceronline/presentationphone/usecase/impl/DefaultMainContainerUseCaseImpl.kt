package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.NotificationRepository
import com.pnam.watchingsocceronline.data.repository.SearchRepository
import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.toSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultMainContainerUseCaseImpl @Inject constructor(
    override val videoRepository: VideoRepository,
    override val searchRepository: SearchRepository,
    override val notificationRepository: NotificationRepository,
    override val userRepository: UserRepository
) : MainContainerUseCase {

    override fun getSearchHistory(searchWord: String?): Flow<List<SearchHistory>> =
        MutableStateFlow("").debounce(300).distinctUntilChanged().map {
            val uid: String? = userRepository.getUid()
            val searchHistory: MutableList<SearchHistory> = if (uid != null) {
                searchRepository.getSearchHistory(uid, searchWord).toMutableList()
            } else {
                mutableListOf()
            }
            searchHistory.addAll(videoRepository.getFilterVideos(searchWord).map { filterVideo ->
                filterVideo.toSearch()
            })
            searchHistory
        }.flowOn(Dispatchers.IO)

    override suspend fun getSearchResult(searchWord: String): List<Video> {
        val uid: String? = userRepository.getUid()
        return if (uid == null) {
            videoRepository.getFilterVideos(searchWord)
        } else {
            videoRepository.getSearchResultVideos(uid, searchWord)
        }

    }

    override suspend fun getNotifications(): List<Notification> =
        notificationRepository.getNotifications()

    override suspend fun getNotification(video: Video) {
        notificationRepository.getNotification(video)
    }

    override suspend fun removeNotification(notification: Notification) {
        notificationRepository.deleteNotification(notification)
    }

    override suspend fun saveVideoDownload(video: Download) = videoRepository.saveDownload(video)

    override suspend fun getVideoDownload(video: Video): Download =
        videoRepository.getVideoDownload(video)
}