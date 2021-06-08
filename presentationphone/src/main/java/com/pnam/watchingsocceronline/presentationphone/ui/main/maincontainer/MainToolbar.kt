package com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer

import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import coil.request.ImageRequest
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.LayoutAvatarBinding
import com.pnam.watchingsocceronline.presentationphone.ui.login.SignInActivity

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
    }

    internal fun onCreate() {
    }

    private val avatarClick: View.OnClickListener by lazy {
        View.OnClickListener {
            /**
             * open user activity
             * */
            activity.apply {
                val options: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        it,
                        ViewCompat.getTransitionName(it)!!
                    )
                openUserActivityForResult(options)
            }
        }
    }

    private val signInClick: View.OnClickListener by lazy {
        View.OnClickListener {
            /**
             * open sign in activity
             * */
            binding.apply {
                val options: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        signIn,
                        ViewCompat.getTransitionName(signIn)!!
                    )
                activity.startActivity(
                    Intent(activity, SignInActivity::class.java),
                    options.toBundle()
                )
                activity.overridePendingTransition(
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

    fun setOpenUserActivityForResult(openUserActivityForResult: (ActivityOptionsCompat) -> Unit) {
        this.openUserActivityForResult = openUserActivityForResult
    }

    private lateinit var openUserActivityForResult: (ActivityOptionsCompat) -> Unit
}