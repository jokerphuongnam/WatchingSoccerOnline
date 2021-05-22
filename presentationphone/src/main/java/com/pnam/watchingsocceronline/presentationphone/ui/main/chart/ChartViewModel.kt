package com.pnam.watchingsocceronline.presentationphone.ui.main.chart

import androidx.lifecycle.MutableLiveData
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData

class ChartViewModel: BaseViewModel() {
    val videosLiveData: MutableLiveData<MutableList<Video>> by lazy {
        MutableLiveData()
    }
    fun getData(){
        val data = FakeData.data.toMutableList().apply {
            sortWith { old, new ->
                old.view.compareTo(new.view)
            }
            reverse()
        }.subList(0, 10)
        videosLiveData.postValue(data)
    }
}