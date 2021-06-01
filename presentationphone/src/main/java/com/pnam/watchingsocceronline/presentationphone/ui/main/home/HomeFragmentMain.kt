package com.pnam.watchingsocceronline.presentationphone.ui.main.home

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragmentMain : MainContainerFragment<HomeViewModelMain>() {
    override val viewModel: HomeViewModelMain by viewModels()

    override fun onCreateContainerView() {
        viewModel.getVideos()
    }
}