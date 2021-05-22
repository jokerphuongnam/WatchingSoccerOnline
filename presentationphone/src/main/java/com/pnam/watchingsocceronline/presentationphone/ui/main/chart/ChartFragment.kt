package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.model.model.SearchHistory
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.main.main.MainFragment
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

class ChartFragment : MainFragment<ChartViewModel>() {

    override val viewModel: ChartViewModel by viewModels()

    override val callbackSearchHistory: ContainerItemCallback<SearchHistory> by lazy {
        object : ContainerItemCallback<SearchHistory> {
            override fun onLongTouch(data: SearchHistory) {
            }

            override fun onClick(data: SearchHistory) {
            }
        }
    }

    override val callbackVideo: ContainerItemCallback<Video> by lazy {
        object : ContainerItemCallback<Video> {
            override fun onLongTouch(data: Video) {
            }

            override fun onClick(data: Video) {
                openVideoBottomSheet(data)
            }
        }
    }

    override fun createView() {
        super.createView()
        viewModel.getData()
        viewModel.videosLiveData.observe {
            videoAdapter.submitList(it.toMutableList())
        }
    }

    internal lateinit var openVideoBottomSheet: (Video) -> Unit
}