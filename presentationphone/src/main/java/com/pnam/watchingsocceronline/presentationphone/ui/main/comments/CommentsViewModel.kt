package com.pnam.watchingsocceronline.presentationphone.ui.main.comments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.CommentsUseCase
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val useCase: CommentsUseCase
) : BaseViewModel() {
    internal val commentLiveData: MutableLiveData<Resource<List<Comment>>> by lazy {
        MutableLiveData()
    }

    internal fun getComment(vid: String) {
        commentLiveData.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            try {
                commentLiveData.postValue(Resource.Success(useCase.getComments(vid)))
            } catch (e: Exception) {
                commentLiveData.postValue(Resource.Error(e))
            }
        }
    }
}