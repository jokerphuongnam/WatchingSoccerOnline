package com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
abstract class MainContainerViewModel constructor(
    private val useCase: MainContainerUseCase
) : BaseViewModel() {

    internal val videosLiveData: MutableLiveData<Resource<MutableList<Video>>> by lazy {
        MutableLiveData()
    }

    internal abstract fun getVideos()

    internal val userLiveData: MutableLiveData<User?> by lazy { MutableLiveData() }

    internal val searchLiveData: MutableLiveData<Resource<MutableList<SearchHistory>>> by lazy { MutableLiveData() }

    internal fun search(searchWord: String? = null) {
        searchLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            useCase.getSearchHistory(searchWord)
                .collect(object : FlowCollector<MutableList<SearchHistory>> {
                    override suspend fun emit(value: MutableList<SearchHistory>) {
                        searchLiveData.postValue(Resource.Success(value))
                    }
                })
        }
    }

    internal val searchResultLiveData: MutableLiveData<Resource<MutableList<Video>>> by lazy { MutableLiveData() }

    internal fun searchResult(searchWord: String) {
        searchResultLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            searchResultLiveData.postValue(Resource.Success(useCase.getSearchResult(searchWord)))
        }
    }

    internal val notificationsLiveData: MutableLiveData<Resource<MutableList<Notification>>> by lazy { MutableLiveData() }

    internal fun notification() {
        notificationsLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            notificationsLiveData.postValue(Resource.Success(useCase.getNotifications()))
        }
    }

    internal fun getNotification(video: Video) {

    }

    internal val videoDownloadLiveData: MutableLiveData<Video> by lazy {
        MutableLiveData()
    }

    internal fun getVideoDownload(video: Video) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                useCase.getVideoDownload(video)
                videoDownloadLiveData.postValue(null)
            } catch (ex: Exception) {
                videoDownloadLiveData.postValue(video)
            }
        }
    }
}