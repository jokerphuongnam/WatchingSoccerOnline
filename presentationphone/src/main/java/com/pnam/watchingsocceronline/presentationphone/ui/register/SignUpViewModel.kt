package com.pnam.watchingsocceronline.presentationphone.ui.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.RegisterUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCase: RegisterUseCase
) : BaseViewModel(){

    internal val registerLiveData: MutableLiveData<Resource<Boolean>> by lazy {
        MutableLiveData()
    }

    internal fun register(user: User) {
        registerLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main){
            try {
                useCase.register(user)
                registerLiveData.postValue(Resource.Success(true))
            }catch (e: Exception){
                e.printStackTrace()
                registerLiveData.postValue(Resource.Error(e))
            }
        }
    }
}