package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.VideoRepository
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.presentationphone.usecase.ChartUseCase
import javax.inject.Inject

class DefaultChartUseCaseImpl @Inject constructor(
    override val videoRepository: VideoRepository
) : ChartUseCase {
    override suspend fun getChart(filter: Filter): MutableList<Video> {
        return videoRepository.getChart(filter)
    }
}