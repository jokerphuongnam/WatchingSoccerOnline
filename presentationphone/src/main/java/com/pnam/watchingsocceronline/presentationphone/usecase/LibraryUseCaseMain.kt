package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.model.model.Video

interface LibraryUseCaseMain {
    suspend fun getHistoryVideos(): MutableList<Video>

    suspend fun getVideosDownload(): MutableList<Video>
}