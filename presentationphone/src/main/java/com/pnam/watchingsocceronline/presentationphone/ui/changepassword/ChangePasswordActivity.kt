package com.pnam.watchingsocceronline.presentationphone.ui.changepassword

import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.android.material.textfield.TextInputLayout
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityChangePasswordBinding
import com.pnam.watchingsocceronline.presentationphone.extension.text
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.ui.user.UserActivity
import com.pnam.watchingsocceronline.presentationphone.utils.NoErrorException
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import com.pnam.watchingsocceronline.presentationphone.utils.passwordRegex
import dagger.hilt.android.AndroidEntryPoint
import java.net.SocketTimeoutException

@AndroidEntryPoint
class ChangePasswordActivity :
    BaseActivity<ActivityChangePasswordBinding, ChangePasswordViewModel>(R.layout.activity_change_password) {
    override val viewModel: ChangePasswordViewModel by viewModels()

    private fun setUpData() {
        intent.getParcelableExtra<User>(UserActivity.USER)?.let { user ->
            binding.user = user.apply {
                password = ""
            }
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
            changePassword.setOnClickListener {
                clearError(
                    password,
                    repeatPassword
                )
                var isSuccess = true
                try {
                    password.error = getString(password.text.passwordRegex())
                    isSuccess = false
                } catch (e: NoErrorException) {
                }
                if (!password.text.equals(repeatPassword.text)) {
                    repeatPassword.error =
                        getString(R.string.repeat_password_need_same_password)
                }
                try {
                    newPassword.error = getString(newPassword.text.passwordRegex())
                    isSuccess = false
                } catch (e: NoErrorException) {
                }
                if (isSuccess) {
                    user?.let {
                        viewModel.getUser(it)
                    }
                }
            }
        }
    }

    private lateinit var show: ProgressDialog

    private fun setUpViewModel() {
        viewModel.apply {
            userLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                        show = ProgressDialog.show(
                            this@ChangePasswordActivity,
                            "",
                            getString(R.string.loading_dialog)
                        )
                        binding.changePasswordError.isVisible = false
                    }
                    is Resource.Success -> {
                        binding.user?.let { user ->
                            if (user.password == it.data!!.password) {
                                user.password = binding.newPassword.text
                                viewModel.changePassword(user)
                            } else {
                                binding.password.error = getString(R.string.wrong_password)
                                show.cancel()
                            }
                        }
                    }
                    is Resource.Error -> {
                        when(it.error){
                            is SocketTimeoutException ->{
                                binding.changePasswordError.apply {
                                    isVisible = true
                                    setText(R.string.timeout)
                                }
                            }
                        }
                        show.cancel()
                    }
                }
            }
            changePasswordLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        show.cancel()
                        finish()
                    }
                    is Resource.Error -> {
                        when(it.error){
                            is SocketTimeoutException ->{
                                binding.changePasswordError.apply {
                                    isVisible = true
                                    setText(R.string.timeout)
                                }
                            }
                        }
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
}