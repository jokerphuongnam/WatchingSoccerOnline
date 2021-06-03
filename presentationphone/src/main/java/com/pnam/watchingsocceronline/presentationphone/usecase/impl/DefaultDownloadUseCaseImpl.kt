package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.presentationphone.usecase.DownloadUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultDownloadUseCaseImpl @Inject constructor(
    override val videoRepository: VideoRepository
) : DownloadUseCase {
    override fun getDownloads(): Flow<List<Download>> {
        return videoRepository.getDownloads()
    }

    override suspend fun removeDownload(download: Download) {
        return videoRepository.removeDownload(download)
    }
}