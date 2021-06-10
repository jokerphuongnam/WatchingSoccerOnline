package com.pnam.watchingsocceronline.presentationphone.ui.user

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityUserBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.ui.changepassword.ChangePasswordActivity
import com.pnam.watchingsocceronline.presentationphone.ui.editprofile.EditProfileActivity
import com.pnam.watchingsocceronline.presentationphone.ui.user.optionsavatar.OptionsAvatarFragment
import com.pnam.watchingsocceronline.presentationphone.ui.user.reviewavatar.ReviewAvatarFragment
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>(R.layout.activity_user) {
    override val viewModel: UserViewModel by viewModels()

    private val imageChoose: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    MediaStore.Images.Media.getBitmap(contentResolver, uri).let { bitmap ->
                        openReviewAvatar(bitmap)
                    }
                }
            }
        }

    private val takePhotoFromCamera: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                (result.data?.extras?.get("data") as Bitmap).apply {
                    openReviewAvatar(this)
                }
            }
        }

    private fun openReviewAvatar(bitmap: Bitmap) {
        ReviewAvatarFragment(
            viewModel.userLiveData.value!!.data!!.uid,
            bitmap
        ).show(
            supportFragmentManager.beginTransaction(),
            ReviewAvatarFragment::class.java.simpleName
        )
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
            avatar.setOnClickListener {
                OptionsAvatarFragment().apply {
                    imageChoose = this@UserActivity.imageChoose
                    takePhotoFromCamera = this@UserActivity.takePhotoFromCamera
                }.show(
                    supportFragmentManager.beginTransaction(),
                    OptionsAvatarFragment::class.java.simpleName
                )
            }
        }
    }

    override fun onCreateView() {
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