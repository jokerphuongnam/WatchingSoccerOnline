package com.pnam.watchingsocceronline.presentationphone.ui.main.writecomment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.usecase.WriteCommentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteCommentViewModel @Inject constructor(
    private val useCase: WriteCommentUseCase
) : BaseViewModel() {
    internal val writeCommentLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData()
    }

    internal fun writeComment(comment: String, vid: String, uid: String? = null) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                useCase.writeComment(comment, vid, uid)
                writeCommentLiveData.postValue(true)
            } catch (e: Exception) {
                e.printStackTrace()
                writeCommentLiveData.postValue(false)
            }
        }
    }
}