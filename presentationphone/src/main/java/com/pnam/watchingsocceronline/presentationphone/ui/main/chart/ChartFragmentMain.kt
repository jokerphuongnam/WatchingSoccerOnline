package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class ChartFragmentMain : MainContainerFragment<ChartViewModelMain>() {
    override val viewModel: ChartViewModelMain by viewModels()

    override fun onCreateContainerView() {
        viewModel.getVideos()
    }
}