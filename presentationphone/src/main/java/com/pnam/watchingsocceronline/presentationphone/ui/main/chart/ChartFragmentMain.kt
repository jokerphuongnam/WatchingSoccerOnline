package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ChartFragmentMain : MainContainerFragment<ChartViewModelMain>() {
    override val viewModel: ChartViewModelMain by viewModels()

    override fun onCreateContainerView() {
    }
}