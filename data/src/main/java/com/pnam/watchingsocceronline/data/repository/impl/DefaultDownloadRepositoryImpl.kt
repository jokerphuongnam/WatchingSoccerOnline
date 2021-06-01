package com.pnam.watchingsocceronline.data.repository.impl

import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.repository.DownloadRepository
import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class DefaultDownloadRepositoryImpl @Inject constructor(
    private val downloadVideo: DownloadVideo
) : DownloadRepository {
    override fun downloadVideo(video: Video): Flow<Int> = downloadVideo.downloadVideo(video)

    override fun cancelDownload(id: Long) = downloadVideo.cancelDownload(id)

    override suspend fun saveDownload(video: Video): Long = suspendCancellableCoroutine {

    }

}