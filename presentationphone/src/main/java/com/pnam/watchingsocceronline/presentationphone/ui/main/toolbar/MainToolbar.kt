package com.pnam.watchingsocceronline.presentationphone.ui.main.toolbar

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ToolbarMainBinding
import com.pnam.watchingsocceronline.presentationphone.ui.user.UserActivity

class MainToolbar(
    private val activity: AppCompatActivity,
    private val binding: ToolbarMainBinding
) {

    internal fun onCreate() {
        binding.avatar.apply {
            setOnClickListener(avatarClick)
        }
    }

    internal fun onResume() {
        activity.setSupportActionBar(binding.toolbar)
    }

    private val userIntent: Intent by lazy {
        Intent(activity, UserActivity::class.java)
    }

    private val avatarClick: View.OnClickListener by lazy {
        View.OnClickListener {
            /**
             * open fragment user
             * */
            activity.apply {
                startActivity(userIntent)
                overridePendingTransition(
                    R.anim.slide_in_bottom,
                    R.anim.slide_out_top
                )
            }
        }
    }
}