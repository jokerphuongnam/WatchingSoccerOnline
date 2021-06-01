package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.usecase.LibraryMainUseCase
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject

class DefaultLibraryMainUseCaseImpl @Inject constructor() : LibraryMainUseCase {
    override suspend fun getHistoryVideos(): MutableList<Video> = suspendCancellableCoroutine {

    }

    override suspend fun getVideosDownload(): MutableList<Video> = suspendCancellableCoroutine {

    }
}