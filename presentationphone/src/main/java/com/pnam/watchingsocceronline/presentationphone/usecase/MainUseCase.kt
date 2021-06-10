package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface MainUseCase {
    val videoRepository: VideoRepository
    val userRepository: UserRepository
    suspend fun getRecommendVideos(): List<Video>
    suspend fun getVideo(vid: String): Video
    suspend fun getUser(): User
    suspend fun getVideoDownload(video: Video): Download
    suspend fun likeVideo(vid: String)
    suspend fun dislikeVideo(vid: String)
}