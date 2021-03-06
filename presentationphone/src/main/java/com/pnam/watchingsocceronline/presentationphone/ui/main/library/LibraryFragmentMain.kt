package com.pnam.watchingsocceronline.presentationphone.ui.main.library

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class LibraryFragmentMain  : MainContainerFragment<LibraryViewModelMain>() {
    override val viewModel: LibraryViewModelMain by viewModels()

    override fun onCreateContainerView() {
        libraryBinding.download.setOnClickListener {
            videoDownload()
        }
    }

    internal fun history() {

    }

    internal lateinit var openDownloadFragment: () -> Unit

    internal fun videoDownload() {
        openDownloadFragment()
    }
}