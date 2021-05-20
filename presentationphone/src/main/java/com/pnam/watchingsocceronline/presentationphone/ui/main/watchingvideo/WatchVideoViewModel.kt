package com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo

import androidx.lifecycle.MutableLiveData
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel

class WatchVideoViewModel: BaseViewModel() {
    val videoLiveData: MutableLiveData<Video> by lazy { MutableLiveData() }
}