package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Download
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface DownloadUseCase {
    val videoRepository: VideoRepository

    fun getDownloads(): Flow<List<Download>>

    suspend fun removeDownload(download: Download)
}