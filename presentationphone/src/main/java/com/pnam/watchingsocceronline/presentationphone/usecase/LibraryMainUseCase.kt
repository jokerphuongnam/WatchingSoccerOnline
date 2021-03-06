package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface LibraryMainUseCase {
    val videoRepository: VideoRepository
    suspend fun getHistoryVideos(): MutableList<Video>

    suspend fun getVideosDownload(): MutableList<Video>
}