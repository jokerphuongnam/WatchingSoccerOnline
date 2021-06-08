package com.pnam.watchingsocceronline.presentationphone.ui.user

import android.app.ProgressDialog
import androidx.activity.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityUserBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>(R.layout.activity_user) {
    override val viewModel: UserViewModel by viewModels()

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.setHomeButtonEnabled(false)
        actionBar.title = null
    }

    private lateinit var show: ProgressDialog

    private fun setUpViewModel() {
        viewModel.apply {
            userLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        binding.user = it.data
                    }
                    is Resource.Error -> {
                    }
                }
            }
            signOutLiveData.observe {
                when (it) {
                    is Resource.Loading -> {
                        show = ProgressDialog.show(
                            this@UserActivity,
                            "",
                            getString(R.string.loading_dialog)
                        )
                    }
                    is Resource.Success -> {
                        finish()
                        show.cancel()
                    }
                    is Resource.Error -> {
                        show.cancel()
                        showToast(R.string.has_error_when_sign_out)
                    }
                }
            }
        }
    }

    private fun setUpEvent() {
        binding.apply {
            back.setOnClickListener { onBackPressed() }
            signOut.setOnClickListener { viewModel.signOut() }
        }
    }

    override fun onCreateView() {
        setUpActionBar()
        setUpViewModel()
        setUpEvent()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_top,
            R.anim.slide_out_bottom
        )
    }
}