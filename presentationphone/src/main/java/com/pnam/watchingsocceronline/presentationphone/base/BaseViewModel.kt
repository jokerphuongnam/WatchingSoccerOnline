package com.pnam.watchingsocceronline.presentationphone.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    /**
     * if don't have internet will change internet error live data
     * */
    val internetError: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}