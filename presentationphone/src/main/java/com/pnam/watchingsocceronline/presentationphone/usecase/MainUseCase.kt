package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface MainUseCase {
    val videoRepository: VideoRepository
    val userRepository: UserRepository
    suspend fun getRecommendVideo(): MutableList<Video>
    suspend fun getVideo(vid: Long): Video
    suspend fun getUser(): User
}