package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.DownloadRepository
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class DefaultMainContainerUseCaseImpl @Inject constructor(
    private val downloadRepository: DownloadRepository
) : MainContainerUseCase {
    override fun getSearchHistory(searchWord: String?): Flow<MutableList<SearchHistory>> = flow {

    }

    override suspend fun getSearchResult(searchWord: String): MutableList<Video> =
        suspendCancellableCoroutine {

        }

    override suspend fun getNotifications(): MutableList<Notification> =
        suspendCancellableCoroutine {

        }

    override suspend fun getNotification(video: Video): Notification = suspendCancellableCoroutine {

    }

    override fun downloadVideo(video: Video): Long = downloadRepository.downloadVideo(video)

    override suspend fun saveVideoDownload(video: Video): Long = downloadRepository.saveDownload(video)
}