package com.pnam.watchingsocceronline.presentationphone.ui.signin

import androidx.activity.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivitySignInBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity

class SignInActivity :
    BaseActivity<ActivitySignInBinding, SignInViewModel>(R.layout.activity_sign_in) {
    override val viewModel: SignInViewModel by viewModels()

    override fun onCreateView() {

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_top,
            R.anim.slide_out_bottom
        )
    }
}