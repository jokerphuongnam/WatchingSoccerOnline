package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.usecase.HomeUseCase
import javax.inject.Inject

class DefaultHomeUseCaseImpl @Inject constructor(
    override val videoRepository: VideoRepository
) : HomeUseCase {
    override suspend fun getVideos(): List<Video> {
        return videoRepository.getVideos()
    }
}