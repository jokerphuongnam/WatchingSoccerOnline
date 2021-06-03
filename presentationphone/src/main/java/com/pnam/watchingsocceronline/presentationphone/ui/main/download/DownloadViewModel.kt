package com.pnam.watchingsocceronline.presentationphone.ui.main.download

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.DownloadUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val downloadUseCase: DownloadUseCase
) : BaseViewModel() {

    internal val videoDownloads: MutableLiveData<Resource<List<Download>>> by lazy {
        MutableLiveData()
    }

    internal fun getVideoDownloads() {
        videoDownloads.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                downloadUseCase.getDownloads().collect { downloads ->
                    videoDownloads.postValue(Resource.Success(downloads))
                }
            } catch (ex: Throwable) {
                videoDownloads.postValue(Resource.Error(ex))
            }
        }
    }

    internal val removeDownload: MutableLiveData<Resource<Boolean>> by lazy {
        MutableLiveData()
    }

    internal fun removeDownload(video: Download) {
        removeDownload.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                downloadUseCase.removeDownload(video)
                removeDownload.postValue(Resource.Success(true))
            } catch (ex: Exception) {
                removeDownload.postValue(Resource.Error(ex))
            }
        }
    }
}