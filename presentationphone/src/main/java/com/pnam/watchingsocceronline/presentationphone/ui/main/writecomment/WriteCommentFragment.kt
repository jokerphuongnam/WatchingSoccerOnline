package com.pnam.watchingsocceronline.presentationphone.ui.main.writecomment

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentWriteCommentBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseBottomSheetDialogFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class WriteCommentFragment :
    BaseBottomSheetDialogFragment<FragmentWriteCommentBinding, WriteCommentViewModel, MainViewModel>(
        R.layout.fragment_write_comment
    ) {
    override val activityViewModel: MainViewModel by activityViewModels()
    override val viewModel: WriteCommentViewModel by viewModels()

    private fun setUpViewModel() {
        viewModel.writeCommentLiveData.observe {
            if (it) {
                dismiss()
            }
        }
    }

    private fun setUpAction() {
        binding.send.setOnClickListener { _ ->
            binding.content.text.toString().let {
                if (it.isNotEmpty()) {
                    viewModel.writeComment(
                        it,
                        activityViewModel.videoLiveData.value!!.data!!.vid,
                        activityViewModel.userLiveData.value!!.data!!.uid
                    )
                    writeCommentSuccess()
                }
            }
        }
    }

    internal lateinit var writeCommentSuccess: ()-> Unit

    override fun onCreateView() {
        setUpViewModel()
        setUpAction()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.userLiveData.value?.data?.let {
            binding.user = it
        }
    }
}