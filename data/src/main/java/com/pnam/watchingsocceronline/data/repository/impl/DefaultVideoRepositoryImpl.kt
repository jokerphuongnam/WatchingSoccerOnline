package com.pnam.watchingsocceronline.data.repository.impl

import com.pnam.watchingsocceronline.data.database.local.DownloadLocal
import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.data.utils.toVideo
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultVideoRepositoryImpl @Inject constructor(
    private val downloadVideo: DownloadVideo,
    private val downloadLocal: DownloadLocal,
    override val videoNetwork: VideoNetwork
) : VideoRepository {

    override suspend fun downloadVideo(
        video: Video,
        callback: DownloadVideo.DownloadVideoCallback
    ) = downloadVideo.downloadVideo(video, callback)

    override fun cancelDownload(id: Long) = downloadVideo.cancelDownload(id)

    override suspend fun saveDownload(video: Download) = downloadLocal.deleteDownload(video)

    override suspend fun removeDownload(video: Download) {
        downloadVideo.deleteVideo(video)
        return downloadLocal.deleteDownload(video)
    }

    override suspend fun getVideos(): List<Video> {
        return videoNetwork.fetchVideos()
    }

    override suspend fun getRecommendVideos(): List<Video> {
        return videoNetwork.fetchRecommendVideos()
    }

    override suspend fun getVideo(vid: String, uid: String?): Video {
        val videoFromNetwork: Video = videoNetwork.fetchVideo(vid, uid)
        return try {
            val videoFromDisk: Download = downloadLocal.findDownload(vid)
            if (videoFromDisk.downloadProcess == 100) {
                videoFromDisk.toVideo(videoFromNetwork)
            } else {
                videoFromNetwork
            }
        } catch (e: Throwable) {
            videoFromNetwork
        }
    }

    override suspend fun getVideoDownload(video: Video): Download {
        return downloadLocal.findDownload(video.vid)
    }

    override fun getDownloads(): Flow<List<Download>> {
        return downloadLocal.findDownloads()
    }

    override suspend fun getChart(filter: Filter): List<Video> {
        return videoNetwork.fetchChart(filter)
    }

    override suspend fun getFilterVideos(searchWord: String): List<Video> {
        return videoNetwork.fetchFilterVideo(searchWord)
    }

    override suspend fun getSearchResultVideos(uid: String, searchWord: String): List<Video> {
        return videoNetwork.fetchSearchResultVideos(uid, searchWord)
    }

    override suspend fun getComments(vid: String): List<Comment> {
        return videoNetwork.fetchComments(vid)
    }

    override suspend fun likeVideo(uid: String, vid: String) {
        videoNetwork.likeVideo(uid, vid)
    }

    override suspend fun dislikeVideo(uid: String, vid: String) {
        videoNetwork.dislikeVideo(uid, vid)
    }
}