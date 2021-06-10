package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class ChartFragmentMain : MainContainerFragment<ChartViewModelMain>() {
    override val viewModel: ChartViewModelMain by viewModels()

    private fun setUpAction(){
        binding.apply {
            filter.setOnCheckedChangeListener { _, checkedId ->
                when(checkedId){
                    R.id.like ->{
                        viewModel.filter = Filter.LIKE
                    }
                    R.id.dislike ->{
                        viewModel.filter = Filter.DISLIKE
                    }
                    R.id.view ->{
                        viewModel.filter = Filter.VIEW
                    }
                }
                viewModel.getVideos()
            }
        }
    }

    override fun onCreateContainerView() {
        binding.filter.isVisible = true
        setUpAction()
    }
}