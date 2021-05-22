package com.pnam.watchingsocceronline.presentationphone.ui.main

import androidx.lifecycle.MutableLiveData
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData

class MainViewModel : BaseViewModel() {
    internal val videoLiveData: MutableLiveData<Video?> by lazy { MutableLiveData() }
    internal val recommendLiveData: MutableLiveData<List<Video>> by lazy { MutableLiveData() }
    internal fun getRecommendVideo() {
        val recommends = FakeData.data.toMutableList()
        for (video in recommends){
            if(video.vid == videoLiveData.value!!.vid){
                recommends.remove(video)
                break
            }
        }
        recommendLiveData.postValue(recommends)
    }
}