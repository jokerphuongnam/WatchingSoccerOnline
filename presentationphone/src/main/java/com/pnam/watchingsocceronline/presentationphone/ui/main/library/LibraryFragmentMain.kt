package com.pnam.watchingsocceronline.presentationphone.ui.main.library

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerFragment
import com.pnam.watchingsocceronline.presentationphone.usecase.LibraryMainUseCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LibraryFragmentMain  : MainContainerFragment<LibraryViewModelMain>() {
    override val viewModel: LibraryViewModelMain by viewModels()

    override fun onCreateContainerView() {

    }

    internal fun history() {

    }

    internal fun downloadVideo(){

    }
}