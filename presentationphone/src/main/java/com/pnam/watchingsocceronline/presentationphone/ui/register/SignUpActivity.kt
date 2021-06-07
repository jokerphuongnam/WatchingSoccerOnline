package com.pnam.watchingsocceronline.presentationphone.ui.register

import android.util.Log
import androidx.activity.viewModels
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivitySignUpBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity

class SignUpActivity: BaseActivity<ActivitySignUpBinding, SignUpViewModel>(R.layout.activity_sign_up) {
    override val viewModel: SignUpViewModel by viewModels()

    private fun setUpData() {
        binding.apply {
            user = User()
        }
    }

    private fun setUpAction() {
        binding.apply {
            register.setOnClickListener {
                binding.apply {
                    user?.gender = User.Gender.values()[genderChoose.selectedItemPosition]
                    Log.e("ccccccccccccccccccc", user.toString())
                }
            }
        }
    }

    override fun onCreateView() {
        setUpData()
        setUpAction()
    }
}