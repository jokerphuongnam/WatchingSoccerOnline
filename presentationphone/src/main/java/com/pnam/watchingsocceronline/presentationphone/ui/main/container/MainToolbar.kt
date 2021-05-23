package com.pnam.watchingsocceronline.presentationphone.ui.main.container

import android.content.Intent
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pnam.watchingsocceronline.model.model.User
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.LayoutAvatarBinding
import com.pnam.watchingsocceronline.presentationphone.ui.user.UserActivity

class MainToolbar(private val activity: AppCompatActivity) {

    private var _binding: LayoutAvatarBinding? = null
    val binding: LayoutAvatarBinding get() = _binding!!

    internal fun setBinding(view: View) {
        _binding = LayoutAvatarBinding.bind(view)
        binding.avatar.apply {
            setOnClickListener(avatarClick)
        }
        binding.avatarHandle = avatarHandle
    }

    internal fun onCreate() {
    }

    private val avatarHandle: Function1<ImageRequest.Builder, Unit> by lazy {
        {
            it.transformations(CircleCropTransformation())
            it.crossfade(true)
            it.placeholder(R.drawable.ic_error)
        }
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

    internal fun setUser(user: User?) {
        binding.user = user
    }
}