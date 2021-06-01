package com.pnam.watchingsocceronline.data.repository

import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface DownloadRepository {
    fun downloadVideo(video: Video): Flow<Int>

    fun cancelDownload(id: Long)

    suspend fun saveDownload(video: Video): Long
}