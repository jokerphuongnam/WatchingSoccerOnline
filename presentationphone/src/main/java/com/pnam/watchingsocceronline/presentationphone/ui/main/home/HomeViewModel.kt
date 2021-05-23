package com.pnam.watchingsocceronline.presentationphone.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class HomeViewModel: ContainerViewModel() {
    val videosLiveData: MutableLiveData<MutableList<Video>> by lazy {
        MutableLiveData()
    }
    fun getData(){
        viewModelScope.launch(Dispatchers.Main){
            videosLiveData.postValue(FakeData.getFakeData())
        }
    }
}