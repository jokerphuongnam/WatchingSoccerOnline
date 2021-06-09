package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.usecase.MainUseCase
import javax.inject.Inject

class DefaultMainUseCaseImpl @Inject constructor(
    override val videoRepository: VideoRepository,
    override val userRepository: UserRepository
) : MainUseCase {
    override suspend fun getRecommendVideos(): List<Video> {
        return videoRepository.getRecommendVideos()
    }

    override suspend fun getVideo(vid: String): Video {
        return videoRepository.getVideo(vid, userRepository.getUid())
    }

    override suspend fun getUser(): User = userRepository.getUser()

    override suspend fun getVideoDownload(video: Video): Download {
        return videoRepository.getVideoDownload(video)
    }
}