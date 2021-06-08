package com.pnam.watchingsocceronline.presentationphone.ui.editprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.EditProfileUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val useCase: EditProfileUseCase
) : BaseViewModel() {

    internal val editProfileLiveData: MutableLiveData<Resource<Boolean>> by lazy { MutableLiveData() }

    fun editProfile(user: User) {
        editProfileLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main){
            try {
                useCase.edit(user)
                editProfileLiveData.postValue(Resource.Success(true))
            }catch (e: Exception){
                editProfileLiveData.postValue(Resource.Error(e))
            }
        }
    }
}