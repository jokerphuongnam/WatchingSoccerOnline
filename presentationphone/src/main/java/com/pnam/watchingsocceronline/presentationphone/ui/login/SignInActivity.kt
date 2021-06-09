package com.pnam.watchingsocceronline.presentationphone.ui.login

import android.app.ProgressDialog
import android.content.Intent
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.pnam.watchingsocceronline.data.throwable.NoConnectivityException
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivitySignInBinding
import com.pnam.watchingsocceronline.presentationphone.extension.text
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.ui.register.SignUpActivity
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity :
    BaseActivity<ActivitySignInBinding, SignInViewModel>(R.layout.activity_sign_in) {
    override val viewModel: SignInViewModel by viewModels()

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.setHomeButtonEnabled(false)
        actionBar.title = null
    }

    private lateinit var show: ProgressDialog

    private fun setUpViewModel() {
        viewModel.userLiveData.observe {
            when (it) {
                is Resource.Loading -> {
                    binding.error.isVisible = false
                    show = ProgressDialog.show(this, "", getString(R.string.loading_dialog))
                }
                is Resource.Success -> {
                    it.data?.let { user -> viewModel.saveUser(user) }
                    show.cancel()
                }
                is Resource.Error -> {
                    @StringRes val errorRes = when (it.error) {
                        is NotFoundException -> {
                            R.string.wrong_username_or_password
                        }
                        is NoConnectivityException -> {
                            R.string.no_internet
                        }
                        else -> {
                            0
                        }
                    }
                    binding.error.apply {
                        setText(errorRes)
                        isVisible = true
                    }
                    show.cancel()
                }
            }
        }
        viewModel.saveUserLiveData.observe {
            if (it) {
                finish()
            } else {
                showToast(R.string.has_error_when_save_user)
            }
        }
    }

    private fun setUpAction() {
        binding.apply {
            backButton.setOnClickListener { onBackPressed() }
            login.setOnClickListener {
                viewModel.login(email.text, password.text)
            }
            register.setOnClickListener {
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
                val options: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@SignInActivity,
                        register, ViewCompat.getTransitionName(register)!!
                    )
                startActivity(
                    Intent(this@SignInActivity, SignUpActivity::class.java),
                    options.toBundle()
                )
            }
        }
    }

    override fun onCreateView() {
        setUpActionBar()
        setUpViewModel()
        setUpAction()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_top,
            R.anim.slide_out_bottom
        )
    }
}