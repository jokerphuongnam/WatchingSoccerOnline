package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.model.model.Video
import javax.inject.Singleton

@Singleton
interface LibraryMainUseCase {
    suspend fun getHistoryVideos(): MutableList<Video>

    suspend fun getVideosDownload(): MutableList<Video>
}