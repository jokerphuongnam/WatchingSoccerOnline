package com.pnam.watchingsocceronline.data.repository

import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface VideoRepository {
    val videoNetwork : VideoNetwork
    suspend fun downloadVideo(video: Video, callback: DownloadVideo.DownloadVideoCallback)
    fun cancelDownload(id: Long)
    suspend fun saveDownload(video: Download)
    suspend fun removeDownload(video: Download)
    suspend fun getVideos(): MutableList<Video>
    suspend fun getVideo(vid: String, uid: String? = null): Video
    suspend fun getVideoDownload(video: Video): Download
    fun getDownloads(): Flow<List<Download>>
    suspend fun getChart(filter: Filter): List<Video>
    suspend fun getFilterVideos(searchWord: String? = null): List<Video>
    suspend fun getComments(vid: String): List<Comment>
}