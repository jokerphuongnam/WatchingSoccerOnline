package com.pnam.watchingsocceronline.presentationphone.ui.main.toolbar

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
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

        binding.avatarHandle = avatarHandle
    }

    private val avatarHandle: Function1<ImageRequest.Builder, Unit> by lazy {
        {
            it.transformations(CircleCropTransformation())
            it.crossfade(true)
            it.placeholder(R.drawable.ic_error)
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