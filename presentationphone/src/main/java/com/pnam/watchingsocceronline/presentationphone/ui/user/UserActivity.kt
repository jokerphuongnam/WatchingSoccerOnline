package com.pnam.watchingsocceronline.presentationphone.ui.user

import android.app.ProgressDialog
import android.content.Intent
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityUserBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.ui.changepassword.ChangePasswordActivity
import com.pnam.watchingsocceronline.presentationphone.ui.editprofile.EditProfileActivity
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>(R.layout.activity_user) {
    override val viewModel: UserViewModel by viewModels()

    private fun setUpData() {
//        intent.getParcelableExtra<User>(USER)?.let {
//            viewModel.userLiveData.postValue(Resource.Success(it))
//            binding.user = it
//        }
    }

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
            editProfile.setOnClickListener {
                val options: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@UserActivity,
                        Pair(it, ViewCompat.getTransitionName(it)!!),
                        Pair(firstName, ViewCompat.getTransitionName(firstName)!!),
                        Pair(lastName, ViewCompat.getTransitionName(lastName)!!),
                        Pair(genderLabel, ViewCompat.getTransitionName(genderLabel)!!),
                        Pair(gender, ViewCompat.getTransitionName(gender)!!),
                        Pair(birthday, ViewCompat.getTransitionName(birthday)!!)
                    )
                startActivity(
                    Intent(this@UserActivity, EditProfileActivity::class.java).apply {
                        putParcelableExtra(USER, viewModel.userLiveData.value!!.data!!)
                    },
                    options.toBundle()
                )
            }
            changePassword.setOnClickListener {
                val options: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this@UserActivity,
                        it,
                        ViewCompat.getTransitionName(it)!!
                    )
                startActivity(
                    Intent(this@UserActivity, ChangePasswordActivity::class.java).apply {
                        putParcelableExtra(USER, viewModel.userLiveData.value!!.data!!)
                    },
                    options.toBundle()
                )
            }
        }
    }

    override fun onCreateView() {
        setUpData()
        setUpActionBar()
        setUpViewModel()
        setUpEvent()
    }

    override fun onResume() {
        super.onResume()
        viewModel.user()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_top,
            R.anim.slide_out_bottom
        )
    }

    companion object {
        const val USER: String = "user"
    }
}