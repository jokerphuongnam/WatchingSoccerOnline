package com.pnam.watchingsocceronline.presentationphone.ui.user.optionsavatar

import android.content.Intent
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentOptionsAvatarBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseBottomSheetDialogFragment
import com.pnam.watchingsocceronline.presentationphone.ui.user.UserViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OptionsAvatarFragment :
    BaseBottomSheetDialogFragment<FragmentOptionsAvatarBinding, OptionsAvatarViewModel, UserViewModel>(
        R.layout.fragment_options_avatar
    ) {
    override val viewModel: OptionsAvatarViewModel by viewModels()
    override val activityViewModel: UserViewModel by activityViewModels()

    private fun setUpData() {
        activityViewModel.userLiveData.value?.data?.avatar?.let { avatar ->
            binding.hasAvatar = !avatar.equals("N/A", false)
        }
    }

    private fun setUpViewModel() {
        viewModel.apply {
            deleteAvatarLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        activityViewModel.user()
                        dismiss()
                    }
                    is Resource.Error -> {
                        dismiss()
                    }
                }
            }
        }
    }

    private fun setUpAction() {
        binding.apply {
            chooseGallery.setOnClickListener {
                imageChoose.launch(Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    type = "image/*"
                    addCategory(Intent.CATEGORY_OPENABLE)
                })
                dismiss()
            }
            openCamera.setOnClickListener {
                takePhotoFromCamera.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
                dismiss()
            }
            delete.setOnClickListener {
                activityViewModel.userLiveData.value?.data?.let { user ->
                    viewModel.deleteAvatar(user.uid)
                }
            }
        }
    }

    override fun onCreateView() {
        setUpData()
        setUpViewModel()
        setUpAction()
    }

    internal lateinit var imageChoose: ActivityResultLauncher<Intent>

    internal lateinit var takePhotoFromCamera: ActivityResultLauncher<Intent>
}