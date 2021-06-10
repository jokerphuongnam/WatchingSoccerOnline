package com.pnam.watchingsocceronline.presentationphone.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.impl.DefaultMainUseCaseImpl
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@HiltViewModel
@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val useCase: DefaultMainUseCaseImpl
) : BaseViewModel() {
    internal val userLiveData: MutableLiveData<Resource<User>> by lazy { MutableLiveData() }
    internal val videoLiveData: MutableLiveData<Resource<Video?>> by lazy { MutableLiveData() }
    internal val recommendLiveData: MutableLiveData<Resource<MutableList<Video>>> by lazy { MutableLiveData() }

    internal fun user() {
        userLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                userLiveData.postValue(Resource.Success(useCase.getUser()))
            } catch (e: Throwable) {
                e.printStackTrace()
                userLiveData.postValue(Resource.Error(e))
            }
        }
    }

    internal fun getRecommendVideos() {
        recommendLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            recommendLiveData.postValue(
                Resource.Success(
                    useCase.getRecommendVideos().toMutableList()
                )
            )
        }
    }

    internal fun openVideo(vid: String) {
        recommendLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                videoLiveData.postValue(Resource.Success(useCase.getVideo(vid)))
            } catch (e: Throwable) {
                videoLiveData.postValue(Resource.Error(e))
            }
        }
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

    internal fun closeVideo() {
        videoLiveData.postValue(Resource.Success(null))
    }

    /**
     * for work load user with each MainContainerFragment
     * */
    internal val userObservers: MutableList<(User?) -> Unit> by lazy {
        mutableListOf()
    }
}