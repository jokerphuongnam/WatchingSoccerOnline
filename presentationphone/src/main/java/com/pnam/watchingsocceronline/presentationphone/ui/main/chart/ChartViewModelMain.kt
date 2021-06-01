package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class ChartViewModelMain @Inject constructor(
    mainContainerUseCase: MainContainerUseCase
) : MainContainerViewModel(mainContainerUseCase) {

    override fun getVideos() {
        videosLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            val data = FakeData.getFakeVideos().toMutableList().apply {
                sortWith { old, new ->
                    old.view.compareTo(new.view)
                }
                reverse()
            }.subList(0, 10)
            videosLiveData.postValue(Resource.Success(data))
        }
    }
}