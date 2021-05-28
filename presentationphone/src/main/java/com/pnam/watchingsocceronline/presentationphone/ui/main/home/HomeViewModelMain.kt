package com.pnam.watchingsocceronline.presentationphone.ui.main.home

import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
class HomeViewModelMain: MainContainerViewModel() {

    override fun getVideos() {
        videosLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            videosLiveData.postValue(Resource.Success(FakeData.getFakeVideos()))
        }
    }
}