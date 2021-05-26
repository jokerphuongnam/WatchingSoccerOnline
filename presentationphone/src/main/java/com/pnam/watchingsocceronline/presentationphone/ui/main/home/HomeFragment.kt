package com.pnam.watchingsocceronline.presentationphone.ui.main.home

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerFragment
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class HomeFragment : ContainerFragment<HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()

    override val videoCallback: ContainerItemCallback<Video> by lazy {
        object : ContainerItemCallback<Video> {
            override fun onLongTouch(data: Video) {
            }

            override fun onClick(data: Video) {
                openVideoBottomSheet(data.vid)
            }
        }
    }

    override fun onCreateContainerView() {
        viewModel.getData()
    }
}