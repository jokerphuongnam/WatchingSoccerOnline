package com.pnam.watchingsocceronline.presentationphone.ui.register

import androidx.activity.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivitySignUpBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity

class SignUpActivity: BaseActivity<ActivitySignUpBinding, SignUpViewModel>(R.layout.activity_sign_up) {
    override val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView() {
        //
    }
}