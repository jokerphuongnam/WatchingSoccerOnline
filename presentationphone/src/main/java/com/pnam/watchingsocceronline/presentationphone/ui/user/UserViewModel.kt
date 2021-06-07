package com.pnam.watchingsocceronline.presentationphone.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.UserUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: UserUseCase
) : BaseViewModel() {

    internal val signOutLiveData: MutableLiveData<Resource<Boolean>> by lazy {
        MutableLiveData()
    }

    internal fun signOut() {
        signOutLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                useCase.signOut()
                signOutLiveData.postValue(Resource.Success(true))
            } catch (e: Exception) {
                signOutLiveData.postValue(Resource.Error(e))
            }
        }
    }
}