package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface MainUseCase {
    suspend fun getRecommendVideo(): MutableList<Video>
    suspend fun getVideo(vid: Long): Video
}