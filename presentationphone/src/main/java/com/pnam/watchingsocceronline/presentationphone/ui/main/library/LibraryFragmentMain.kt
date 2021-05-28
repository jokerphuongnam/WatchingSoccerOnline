package com.pnam.watchingsocceronline.presentationphone.ui.main.library

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class LibraryFragmentMain : MainContainerFragment<LibraryViewModelMain>() {
    override val viewModel: LibraryViewModelMain by viewModels()

    override fun onCreateContainerView() {

    }

    internal fun history(){

    }

    internal fun downloadVideo(){

    }
}