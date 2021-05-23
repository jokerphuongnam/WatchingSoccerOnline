package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class ChartViewModel: ContainerViewModel() {
    val videosLiveData: MutableLiveData<MutableList<Video>> by lazy {
        MutableLiveData()
    }
    fun getData(){
        viewModelScope.launch(Dispatchers.Main){
            val data = FakeData.getFakeData().toMutableList().apply {
                sortWith { old, new ->
                    old.view.compareTo(new.view)
                }
                reverse()
            }.subList(0, 10)
            videosLiveData.postValue(data)
        }
    }
}