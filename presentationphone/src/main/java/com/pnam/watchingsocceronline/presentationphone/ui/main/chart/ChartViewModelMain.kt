package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.ChartUseCase
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@FlowPreview
@HiltViewModel
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class ChartViewModelMain @Inject constructor(
    private val useCase: ChartUseCase,
    mainContainerUseCase: MainContainerUseCase
) : MainContainerViewModel(mainContainerUseCase) {

    override fun getVideos() {
        videosLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            videosLiveData.postValue(Resource.Success(useCase.getChart(filter)))
        }
    }

    internal var filter: Filter = Filter.VIEW
}