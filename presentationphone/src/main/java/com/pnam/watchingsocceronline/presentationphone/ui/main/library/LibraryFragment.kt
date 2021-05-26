package com.pnam.watchingsocceronline.presentationphone.ui.main.library

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class LibraryFragment : ContainerFragment<LibraryViewModel>() {
    override val viewModel: LibraryViewModel by viewModels()

    override fun onCreateContainerView() {

    }
}