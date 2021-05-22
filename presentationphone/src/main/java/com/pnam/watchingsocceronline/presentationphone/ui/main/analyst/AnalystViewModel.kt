package com.pnam.watchingsocceronline.presentationphone.ui.main.analyst

import androidx.lifecycle.MutableLiveData
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData

class AnalystViewModel: BaseViewModel() {
    val videosLiveData: MutableLiveData<MutableList<Video>> by lazy {
        MutableLiveData()
    }
    fun getData(){
        val data = FakeData.data.toMutableList()
        data.sortWith { old, new ->
            old.view.compareTo(new.view)
        }
        videosLiveData.postValue(data)
    }
}