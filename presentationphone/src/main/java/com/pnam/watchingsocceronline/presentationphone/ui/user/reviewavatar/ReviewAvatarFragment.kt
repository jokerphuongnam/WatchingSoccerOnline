package com.pnam.watchingsocceronline.presentationphone.ui.user.reviewavatar

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentReviewAvatarBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseBottomSheetDialogFragment
import com.pnam.watchingsocceronline.presentationphone.ui.user.UserViewModel
import com.pnam.watchingsocceronline.presentationphone.ui.user.optionsavatar.OptionsAvatarFragment
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewAvatarFragment(
    private val uid: String,
    private val avatarBitmap: Bitmap
) : BaseBottomSheetDialogFragment<FragmentReviewAvatarBinding, ReviewAvatarViewModel, UserViewModel>(
    R.layout.fragment_review_avatar
) {
    override val activityViewModel: UserViewModel by activityViewModels()
    override val viewModel: ReviewAvatarViewModel by viewModels()

    private val imageChoose: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
                        .let { bitmap ->
                            loadAvatar(bitmap)
                        }
                }
            }
        }

    private val takePhotoFromCamera: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                (result.data?.extras?.get("data") as Bitmap).apply {
                    loadAvatar(this)
                }
            }
        }


    private fun loadAvatar(bitmap: Bitmap) {
        viewModel.avatar = bitmap
        binding.avatar.load(bitmap, builder = {
            transformations(CircleCropTransformation())
            crossfade(true)
            placeholder(R.drawable.ic_error)
        })
    }

    private fun setUpData() {
        loadAvatar(avatarBitmap)
        viewModel.uid = uid
    }

    private fun setUpViewModel() {
        viewModel.apply {
            uploadAvatarLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                        isCancelable = false
                    }
                    is Resource.Success -> {
                        isCancelable = true
                        showToast(R.string.upload_avatar_success)
                        activityViewModel.user()
                        dismiss()
                    }
                    is Resource.Error -> {
                        isCancelable = true
                    }
                }
            }
        }
    }

    private fun setAction() {
        binding.apply {
            avatar.setOnClickListener {
                OptionsAvatarFragment().apply {
                    imageChoose = this@ReviewAvatarFragment.imageChoose
                    takePhotoFromCamera = this@ReviewAvatarFragment.takePhotoFromCamera
                }.show(
                    requireActivity().supportFragmentManager.beginTransaction(),
                    OptionsAvatarFragment::class.java.simpleName
                )
            }
            uploadAvatar.setOnClickListener {
                viewModel.uploadAvatar()
            }
        }
    }

    override fun onCreateView() {
        setUpData()
        setUpViewModel()
        setAction()
    }
}