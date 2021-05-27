package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class ChartViewModel: ContainerViewModel() {

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