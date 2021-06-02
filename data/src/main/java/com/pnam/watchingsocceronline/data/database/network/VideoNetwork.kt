package com.pnam.watchingsocceronline.data.database.network

import com.pnam.watchingsocceronline.domain.model.Video

interface VideoNetwork {
    suspend fun fetchVideos(): MutableList<Video>
    suspend fun fetchVideo(vid: String, uid: String): Video
}