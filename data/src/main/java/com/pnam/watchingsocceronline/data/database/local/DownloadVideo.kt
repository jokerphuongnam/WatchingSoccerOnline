package com.pnam.watchingsocceronline.data.database.local

import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface DownloadVideo {
    fun downloadVideo(video: Video): Flow<Int>

    fun cancelDownload(id: Long)
}