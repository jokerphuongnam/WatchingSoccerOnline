package com.pnam.watchingsocceronline.presentationphone.ui.editprofile

import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityEditProfileBinding
import com.pnam.watchingsocceronline.presentationphone.extension.text
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.ui.user.UserActivity.Companion.USER
import com.pnam.watchingsocceronline.presentationphone.utils.NoErrorException
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import com.pnam.watchingsocceronline.presentationphone.utils.nameRegex
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileActivity :
    BaseActivity<ActivityEditProfileBinding, EditProfileViewModel>(R.layout.activity_edit_profile) {
    override val viewModel: EditProfileViewModel by viewModels()

    private fun setUpData() {
        intent.getParcelableExtra<User>(USER)?.let {
            binding.user = it
        }
    }

    private fun clearError(vararg textInputLayout: TextInputLayout) {
        textInputLayout.forEach {
            it.error = null
        }
    }

    private fun setUpAction() {
        binding.apply {
            back.setOnClickListener { onBackPressed() }
            editProfile.setOnClickListener {
                clearError(
                    firstName,
                    lastName,
                    birthday
                )
                user?.gender = User.Gender.values()[genderChoose.selectedItemPosition]
                var isSuccess = true
                try {
                    firstName.error = getString(firstName.text.nameRegex())
                    isSuccess = false
                } catch (e: NoErrorException) {
                }
                try {
                    lastName.error = getString(lastName.text.nameRegex())
                    isSuccess = false
                } catch (e: NoErrorException) {
                }
                user?.takeIf { it.birthDay >= System.currentTimeMillis() }?.let {
                    isSuccess = false
                    birthday.error = getString(R.string.birthday_need_before_present_time)
                }
                if (isSuccess) {
                    user?.let {
                        viewModel.editProfile(it)
                    }
                }
            }
        }
    }

    private lateinit var show: ProgressDialog

    private fun setUpViewModel() {
        viewModel.apply {
            editProfileLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                        show = ProgressDialog.show(
                            this@EditProfileActivity,
                            "",
                            getString(R.string.loading_dialog)
                        )
                        binding.editProfileError.isVisible = false
                    }
                    is Resource.Success -> {
                        show.cancel()
                        finish()
                    }
                    is Resource.Error -> {
                        show.cancel()
                    }
                }
            }
        }
    }

    override fun onCreateView() {
        setUpData()
        setUpAction()
        setUpViewModel()
    }

    override fun onResume() {
        super.onResume()
    }
}