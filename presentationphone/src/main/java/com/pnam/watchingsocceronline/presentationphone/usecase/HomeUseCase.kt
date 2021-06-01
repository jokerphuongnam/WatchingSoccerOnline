package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface HomeUseCase {
    suspend fun getVideos(): MutableList<Video>
}