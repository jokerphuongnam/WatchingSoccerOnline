package com.pnam.watchingsocceronline.presentationphone.ui.main.home

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class HomeFragment : ContainerFragment<HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()

    override fun onCreateContainerView() {
        viewModel.getData()
    }
}