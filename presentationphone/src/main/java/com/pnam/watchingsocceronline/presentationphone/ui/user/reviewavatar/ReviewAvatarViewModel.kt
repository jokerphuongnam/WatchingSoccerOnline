package com.pnam.watchingsocceronline.presentationphone.ui.user.reviewavatar

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.ReviewAvatarUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewAvatarViewModel @Inject constructor(
    private val useCase: ReviewAvatarUseCase
) : BaseViewModel() {

    internal lateinit var avatar: Bitmap
    internal lateinit var uid: String

    internal val uploadAvatarLiveData: MutableLiveData<Resource<Boolean>> by lazy { MutableLiveData() }

    internal fun uploadAvatar() {
        uploadAvatarLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                useCase.changeAvatar(uid, avatar)
                uploadAvatarLiveData.postValue(Resource.Success(true))
            } catch (e: Exception) {
                uploadAvatarLiveData.postValue(Resource.Error(e))
            }
        }
    }
}