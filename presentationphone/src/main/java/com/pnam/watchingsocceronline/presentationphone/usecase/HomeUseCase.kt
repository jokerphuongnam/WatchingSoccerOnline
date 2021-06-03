package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface HomeUseCase {
    val videoRepository: VideoRepository
    suspend fun getVideos(): MutableList<Video>
}