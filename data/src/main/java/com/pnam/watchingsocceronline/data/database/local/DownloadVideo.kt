package com.pnam.watchingsocceronline.data.database.local

import android.net.Uri
import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface DownloadVideo {
    fun downloadVideo(video: Video, dir: (Uri) -> Unit): Flow<Int>

    fun cancelDownload(id: Long)
}