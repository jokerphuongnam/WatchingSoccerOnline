package com.pnam.watchingsocceronline.data.database.network

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface VideoNetwork {
    suspend fun fetchVideos(): List<Video>

    @Throws(Resources.NotFoundException::class)
    suspend fun fetchVideo(vid: String, uid: String? = null): Video

    @Throws(Resources.NotFoundException::class)
    suspend fun fetchComments(vid: String): List<Comment>
    suspend fun writeComment(content: String, vid: String, uid: String): Comment
    suspend fun fetchChart(filter: Filter): List<Video>
    suspend fun fetchFilterVideo(searchWord: String): List<Video>
    suspend fun fetchSearchResultVideos(uid: String, searchWord: String): List<Video>
    suspend fun fetchRecommendVideos(): List<Video>
    suspend fun likeVideo(uid: String, vid: String): Video
    suspend fun dislikeVideo(uid: String, vid: String): Video
}