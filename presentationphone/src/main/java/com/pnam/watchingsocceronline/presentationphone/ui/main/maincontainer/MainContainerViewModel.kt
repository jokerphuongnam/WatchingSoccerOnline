package com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.MainContainerUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@FlowPreview
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
            FakeData.getFakeSearchHistory(searchWord).collect {
                searchLiveData.postValue(Resource.Success(it))
            }
        }
    }

    internal val searchResultLiveData: MutableLiveData<Resource<MutableList<Video>>> by lazy { MutableLiveData() }

    internal fun searchResult(searchWord: String) {
        searchResultLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            val searchResult =
                FakeData.getFakeVideos().filter { it.title.contains(searchWord) }.toMutableList()
            searchResultLiveData.postValue(Resource.Success(searchResult))
        }
    }

    internal val notificationsLiveData: MutableLiveData<Resource<MutableList<Notification>>> by lazy { MutableLiveData() }

    internal fun notification() {
        notificationsLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            notificationsLiveData.postValue(Resource.Success(FakeData.getFakeNotification()))
        }
    }

    internal fun getNotification(video: Video) {

    }
}