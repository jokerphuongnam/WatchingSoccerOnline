package com.pnam.watchingsocceronline.data.repository

import android.net.Uri
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface VideoRepository {
    fun downloadVideo(video: Video, dir: (Uri) -> Unit): Flow<Int>

    fun cancelDownload(id: Long)

    suspend fun saveDownload(video: Download)

    suspend fun removeDownload(video: Download)

    suspend fun getVideo(vid: String): Video
}