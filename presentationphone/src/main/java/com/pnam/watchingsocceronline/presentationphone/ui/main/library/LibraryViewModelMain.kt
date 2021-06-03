package com.pnam.watchingsocceronline.presentationphone.ui.main.library

import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.LibraryMainUseCase
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@FlowPreview
@HiltViewModel
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class LibraryViewModelMain @Inject constructor(
    private val useCase: LibraryMainUseCase,
    mainContainerUseCase: MainContainerUseCase
) : MainContainerViewModel(mainContainerUseCase) {
    override fun getVideos() {}
}