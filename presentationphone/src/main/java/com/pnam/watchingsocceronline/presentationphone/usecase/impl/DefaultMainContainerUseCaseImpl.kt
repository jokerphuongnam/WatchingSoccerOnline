package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.SearchRepository
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.toSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class DefaultMainContainerUseCaseImpl @Inject constructor(
    override val videoRepository: VideoRepository,
    override val searchRepository: SearchRepository,
) : MainContainerUseCase {

    override fun getSearchHistory(searchWord: String?): Flow<MutableList<SearchHistory>> =
        MutableStateFlow("").debounce(300).distinctUntilChanged().map {
            val searchHistory: MutableList<SearchHistory> =
                searchRepository.getSearchHistory(searchWord)
            searchHistory.addAll(videoRepository.getFilterVideos(searchWord).map { filterVideo ->
                filterVideo.toSearch()
            })
            searchHistory
        }.flowOn(Dispatchers.IO)

    override suspend fun getSearchResult(searchWord: String): List<Video> =
        videoRepository.getFilterVideos(searchWord)

    override suspend fun getNotifications(): MutableList<Notification> =
        suspendCancellableCoroutine {

        }

    override suspend fun getNotification(video: Video): Notification = suspendCancellableCoroutine {

    }

    override suspend fun saveVideoDownload(video: Download) = videoRepository.saveDownload(video)

    override suspend fun getVideoDownload(video: Video): Download =
        videoRepository.getVideoDownload(video)
}