package com.pnam.watchingsocceronline.presentationphone.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.SignInUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val useCase: SignInUseCase
) : BaseViewModel() {
    internal val userLiveData: MutableLiveData<Resource<User>> by lazy {
        MutableLiveData()
    }

    internal val saveUserLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    internal fun login(email: String, password: String) {
        userLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                userLiveData.postValue(Resource.Success(useCase.login(email, password)))
            } catch (e: Exception) {
                userLiveData.postValue(Resource.Error(e))
            }
        }
    }

    internal fun saveUser(user:User){
        viewModelScope.launch(Dispatchers.Main) {
            try {
                useCase.saveUser(user)
                saveUserLiveData.postValue(true)
            }catch (e: Exception){
                saveUserLiveData.postValue(false)
            }
        }
    }
}