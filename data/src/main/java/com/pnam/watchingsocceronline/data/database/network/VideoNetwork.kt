package com.pnam.watchingsocceronline.data.database.network

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface VideoNetwork {
    suspend fun fetchVideos(): MutableList<Video>

    @Throws(Resources.NotFoundException::class)
    suspend fun fetchVideo(vid: Long, uid: Long? = null): Video

    @Throws(Resources.NotFoundException::class)
    suspend fun fetchComments(vid: Long): List<Comment>
    suspend fun writeComment(content: String, vid: Long, uid: Long? = null)
    suspend fun fetchChart(filter: Filter): List<Video>
    suspend fun fetchFilterVideo(searchWord: String? = null): List<Video>
}