package com.pnam.watchingsocceronline.presentationphone.ui.main.comment

import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentCommentBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseBottomSheetDialogFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@FlowPreview
@ExperimentalCoroutinesApi
class CommentFragment :
    BaseBottomSheetDialogFragment<FragmentCommentBinding, CommentViewModel, MainViewModel>(
        R.layout.fragment_comment
    ) {
    override val activityViewModel: MainViewModel by activityViewModels()
    override val viewModel: CommentViewModel by viewModels()

    private val commentsAdapter: CommentsAdapter by lazy { CommentsAdapter() }

    override fun onCreateView() {
        setupFullHeight()
        binding.comments.adapter = commentsAdapter
        activityViewModel.apply {
            videoLiveData.value?.data?.let {
                commentsAdapter.submitList(it.comments)
            }
            binding.avatarHandle = avatarHandle
        }
    }
}