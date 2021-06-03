package com.pnam.watchingsocceronline.data.database.local

import android.net.Uri
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface DownloadVideo {
    interface DownloadVideoCallback {
        fun uri(uri: Uri)
        fun process(process: Int)
    }

    fun downloadVideo(video: Video, callback: DownloadVideoCallback)

    fun cancelDownload(id: Long)

    fun deleteVideo(video: Download)
}