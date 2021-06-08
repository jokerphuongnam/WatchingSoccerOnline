package com.pnam.watchingsocceronline.presentationphone.ui.changepassword

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.ChangePasswordUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val useCase: ChangePasswordUseCase
) : BaseViewModel() {

    internal val userLiveData: MutableLiveData<Resource<User>> by lazy { MutableLiveData() }

    internal fun getUser(user: User) {
        userLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                userLiveData.postValue(Resource.Success(useCase.getUser(user.uid)))
            } catch (e: Exception) {
                userLiveData.postValue(Resource.Error(e))
            }
        }
    }

    internal val changePasswordLiveData: MutableLiveData<Resource<Boolean>> by lazy { MutableLiveData() }

    internal fun changePassword(user: User) {
        changePasswordLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                useCase.changePassword(user)
                changePasswordLiveData.postValue(Resource.Success(true))
            } catch (e: Exception) {
                changePasswordLiveData.postValue(Resource.Error(e))
            }
        }
    }
}