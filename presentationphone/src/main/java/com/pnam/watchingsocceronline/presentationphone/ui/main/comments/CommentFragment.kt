package com.pnam.watchingsocceronline.presentationphone.ui.main.comments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentCommentsBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseBottomSheetDialogFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.MainViewModel
import com.pnam.watchingsocceronline.presentationphone.ui.main.writecomment.WriteCommentFragment
import com.pnam.watchingsocceronline.presentationphone.ui.signin.SignInActivity
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@AndroidEntryPoint
@ExperimentalCoroutinesApi
class CommentFragment(private val appCompatActivity: AppCompatActivity) :
    BaseBottomSheetDialogFragment<FragmentCommentsBinding, CommentsViewModel, MainViewModel>(
        R.layout.fragment_comments
    ) {
    override val activityViewModel: MainViewModel by activityViewModels()
    override val viewModel: CommentsViewModel by viewModels()

    private val commentsAdapter: CommentsAdapter by lazy { CommentsAdapter() }

    private fun setUpViewModel() {
        binding.avatarHandle = activityViewModel.avatarHandle
        viewModel.commentLiveData.observe {
            when (it) {
                is Resource.Loading -> {
                    binding.commentsContainer.isVisible = false
                    binding.loading.isVisible = true
                }
                is Resource.Success -> {
                    binding.commentsContainer.isVisible = true
                    binding.loading.isVisible = false
                    commentsAdapter.submitList(it.data?.toMutableList())
                    it.data?.let { comments ->
                        activityViewModel.videoLiveData.value?.data?.comments = comments
                    }
                    binding.commentsCount = it.data?.size
                }
                is Resource.Error -> {
                    binding.commentsContainer.isVisible = true
                    binding.loading.isVisible = false
                }
            }
        }
    }

    private fun setUpAdapter() {
        binding.comments.adapter = commentsAdapter
    }

    private fun setUpAction() {
        binding.apply {
            close.setOnClickListener { dismiss() }
            writeComment.setOnClickListener {
                activityViewModel.userLiveData.apply {
                    if (value == null || value!!.data == null) {
                        requireActivity().startActivity(
                            Intent(
                                requireContext(),
                                SignInActivity::class.java
                            )
                        )
                    } else {
                        WriteCommentFragment().apply {
                            show(appCompatActivity.supportFragmentManager, javaClass.simpleName)
                            writeCommentSuccess = ::reloadComment
                        }
                    }
                }
            }
        }
    }

    private fun reloadComment() {
        activityViewModel.videoLiveData.value?.data?.let {
            viewModel.getComment(it.vid)
        }
    }

    override fun onCreateView() {
        setupFullHeight()
        setUpViewModel()
        setUpAdapter()
        setUpAction()
    }

    override fun onResume() {
        super.onResume()
        reloadComment()
    }
}