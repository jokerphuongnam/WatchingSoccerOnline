package com.pnam.watchingsocceronline.presentationphone.ui.main.analyst

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.model.model.SearchHistory
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.main.main.MainFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.main.VideosAdapter
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback

class AnalystFragment : MainFragment<AnalystViewModel>() {

    override val viewModel: AnalystViewModel by viewModels()

    private val callbackSearchHistory: ContainerItemCallback<SearchHistory> by lazy {
        object : ContainerItemCallback<SearchHistory> {
            override fun onLongTouch(data: SearchHistory) {
            }

            override fun onClick(data: SearchHistory) {
            }
        }
    }

    private val callbackVideo: ContainerItemCallback<Video> by lazy {
        object : ContainerItemCallback<Video> {
            override fun onLongTouch(data: Video) {
            }

            override fun onClick(data: Video) {
                openVideoBottomSheet(data)
            }
        }
    }

    private val videoAdapter: VideosAdapter by lazy {
        VideosAdapter(callbackSearchHistory, callbackVideo)
    }

    override fun setUpRecycler() {
        super.setUpRecycler()
        binding.videos.apply {
            adapter = videoAdapter
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