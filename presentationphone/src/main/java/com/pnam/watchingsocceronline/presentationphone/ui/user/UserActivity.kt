package com.pnam.watchingsocceronline.presentationphone.ui.user

import android.view.MenuItem
import androidx.activity.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityUserBinding

class UserActivity : BaseActivity<ActivityUserBinding, UserViewModel>(R.layout.activity_user) {
    override val viewModel: UserViewModel by viewModels()

    private fun setUpActionBar() {
        setSupportActionBar(binding.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.setHomeButtonEnabled(false)
        actionBar.title = null
    }

    private fun setUpEvent(){
        binding.backButton.setOnClickListener { onBackPressed() }
    }

    override fun onCreateView() {
        setUpActionBar()
        setUpEvent()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.slide_in_top,
            R.anim.slide_out_bottom
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}