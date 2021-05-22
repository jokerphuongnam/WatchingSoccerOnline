package com.pnam.watchingsocceronline.presentationphone.ui.main

import androidx.lifecycle.MutableLiveData
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel

class MainViewModel:BaseViewModel() {
    internal val videoLiveData: MutableLiveData<Video?> by lazy { MutableLiveData() }
}