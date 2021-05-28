package com.pnam.watchingsocceronline.presentationphone.ui.comment

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentCommentBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseBottomSheetDialogFragment

class CommentFragment: BaseBottomSheetDialogFragment<FragmentCommentBinding, CommentViewModel>(R.layout.fragment_comment) {
    override val viewModel: CommentViewModel by viewModels()

    override fun onCreateView() {

    }
}