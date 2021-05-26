package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerFragment
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class ChartFragment : ContainerFragment<ChartViewModel>() {
    override val viewModel: ChartViewModel by viewModels()

    override val videoCallback: ContainerItemCallback<Video>? = null

    override fun onCreateContainerView() {
        viewModel.getData()
    }
}