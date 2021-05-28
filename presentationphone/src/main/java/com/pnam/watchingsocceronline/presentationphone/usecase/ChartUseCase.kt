package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.model.model.Video


interface ChartUseCase {
    suspend fun getVideos(): MutableList<Video>
}