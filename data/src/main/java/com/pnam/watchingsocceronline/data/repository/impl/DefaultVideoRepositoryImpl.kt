package com.pnam.watchingsocceronline.data.repository.impl

import android.net.Uri
import com.pnam.watchingsocceronline.data.database.local.DownloadLocal
import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.data.utils.toVideo
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultVideoRepositoryImpl @Inject constructor(
    private val downloadVideo: DownloadVideo,
    private val downloadLocal: DownloadLocal
) : VideoRepository {
    override fun downloadVideo(video: Video, dir: (Uri) -> Unit): Flow<Int> =
        downloadVideo.downloadVideo(video, dir)

    override fun cancelDownload(id: Long) = downloadVideo.cancelDownload(id)

    override suspend fun saveDownload(video: Download) = downloadLocal.deleteDownload(video)

    override suspend fun removeDownload(video: Download) {
        return downloadLocal.deleteDownload(video)
    }

    override suspend fun getVideo(vid: String): Video {
        return downloadLocal.getDownload(vid).toVideo(
            Video(
                vid,
                "",
                "",
                "",
                123,
                1111111111,
                "",
                "",
                "",
                mutableListOf()
            )
        )
    }

}