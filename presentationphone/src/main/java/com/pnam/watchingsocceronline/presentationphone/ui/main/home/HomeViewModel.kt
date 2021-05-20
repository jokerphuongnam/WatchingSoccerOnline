package com.pnam.watchingsocceronline.presentationphone.ui.main.home

import androidx.lifecycle.MutableLiveData
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData

class HomeViewModel: BaseViewModel() {
    val videosLiveData: MutableLiveData<MutableList<Video>> by lazy {
        MutableLiveData()
    }
    fun getData(){
        videosLiveData.postValue(FakeData.data)
    }
}