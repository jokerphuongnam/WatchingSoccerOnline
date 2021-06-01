package com.pnam.watchingsocceronline.presentationphone.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pnam.watchingsocceronline.model.model.User
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.FakeData
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    internal val userLiveData: MutableLiveData<Resource<User>> by lazy { MutableLiveData() }
    internal val videoLiveData: MutableLiveData<Resource<Video?>> by lazy { MutableLiveData() }
    internal val recommendLiveData: MutableLiveData<Resource<MutableList<Video>>> by lazy { MutableLiveData() }

    internal fun user() {
        recommendLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            userLiveData.postValue(Resource.Success(FakeData.getFakeUser()))
        }
    }

    internal fun getRecommendVideo() {
        recommendLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            recommendLiveData.postValue(Resource.Success(FakeData.getFakeVideos().toMutableList()))
        }
    }

    internal fun openVideo(vid: String) {
        recommendLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            val recommends = FakeData.getFakeVideos().toMutableList()
            for (video in recommends) {
                if (video.vid == vid) {
                    videoLiveData.postValue(Resource.Success(video))
                    break
                }
            }
        }
    }

    internal fun closeVideo() {
        videoLiveData.postValue(Resource.Success(null))
    }

    internal val avatarHandle: Function1<ImageRequest.Builder, Unit> by lazy {
        {
            it.transformations(CircleCropTransformation())
            it.crossfade(true)
            it.placeholder(R.drawable.ic_error)
        }
    }

    internal val userObservers: MutableList<(User) -> Unit> by lazy {
        mutableListOf()
    }
}