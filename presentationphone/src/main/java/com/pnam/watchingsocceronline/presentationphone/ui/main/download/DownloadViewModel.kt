package com.pnam.watchingsocceronline.presentationphone.ui.main.download

import androidx.lifecycle.MutableLiveData
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.Resource

class DownloadViewModel : BaseViewModel() {

    internal val videoDownloads: MutableLiveData<Resource<MutableList<Download>>> by lazy {
        MutableLiveData()
    }

    internal fun getVideoDownloads() {

    }
}