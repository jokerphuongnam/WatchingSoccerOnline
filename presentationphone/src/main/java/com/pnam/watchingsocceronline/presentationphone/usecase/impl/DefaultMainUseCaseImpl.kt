package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.usecase.MainUseCase
import javax.inject.Inject

class DefaultMainUseCaseImpl @Inject constructor(
    override val videoRepository: VideoRepository,
    override val userRepository: UserRepository
) : MainUseCase {
    override suspend fun getRecommendVideo(): MutableList<Video> {
        return videoRepository.getVideos()
    }

    override suspend fun getVideo(vid: Long): Video {
        return videoRepository.getVideo(vid, userRepository.getUid())
    }

    override suspend fun getUser(): User = userRepository.getUser()
}