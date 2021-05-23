package com.pnam.watchingsocceronline.presentationphone.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel : BaseViewModel() {
    internal val videoLiveData: MutableLiveData<Video?> by lazy { MutableLiveData() }
    internal val recommendLiveData: MutableLiveData<List<Video>> by lazy { MutableLiveData() }

    internal fun getRecommendVideo() {
        viewModelScope.launch(Dispatchers.Main) {
            val recommends = FakeData.getFakeData().toMutableList()
            for (video in recommends) {
                if (video.vid == videoLiveData.value!!.vid) {
                    recommends.remove(video)
                    break
                }
            }
            recommendLiveData.postValue(recommends)
        }
    }

    internal fun openVideo(vid: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            val recommends = FakeData.getFakeData().toMutableList()
            for (video in recommends) {
                if (video.vid == vid) {
                    videoLiveData.postValue(video)
                    break
                }
            }
        }
    }
}