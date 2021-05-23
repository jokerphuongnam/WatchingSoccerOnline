package com.pnam.watchingsocceronline.presentationphone.ui.main.library

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerFragment
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

class LibraryFragment : ContainerFragment<LibraryViewModel>() {
    override val viewModel: LibraryViewModel by viewModels()

    override val videoCallback: ContainerItemCallback<Video>? = null

    override fun onCreateContainerView() {

    }
}