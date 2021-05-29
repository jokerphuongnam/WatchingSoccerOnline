package com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer

import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentActivity
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.pnam.watchingsocceronline.model.model.User
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.LayoutAvatarBinding
import com.pnam.watchingsocceronline.presentationphone.ui.signin.SignInActivity
import com.pnam.watchingsocceronline.presentationphone.ui.user.UserActivity

class MainToolbar(private val activity: FragmentActivity) {

    private var _binding: LayoutAvatarBinding? = null
    val binding: LayoutAvatarBinding get() = _binding!!

    internal fun setBinding(view: View) {
        _binding = LayoutAvatarBinding.bind(view)
        binding.avatar.apply {
            setOnClickListener(avatarClick)
        }
        binding.signIn.apply {
            setOnClickListener(signInClick)
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
             * open user activity
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

    private val signInIntent: Intent by lazy {
        Intent(activity, SignInActivity::class.java)
    }

    private val signInClick: View.OnClickListener by lazy {
        View.OnClickListener {
            /**
             * open sign in activity
             * */
            activity.apply {
                startActivity(signInIntent)
                overridePendingTransition(
                    R.anim.slide_in_bottom,
                    R.anim.slide_out_top
                )
            }
        }
    }

    internal fun setUser(user: User?, builder: ImageRequest.Builder.() -> Unit) {
        binding.avatarHandle = builder
        setUser(user)
    }

    internal fun setUser(user: User?) {
        binding.user = user
    }
}