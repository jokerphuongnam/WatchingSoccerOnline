package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Singleton

@Singleton
interface ChartUseCase {
    val videoRepository: VideoRepository
    suspend fun getChart(filter: Filter): List<Video>
}