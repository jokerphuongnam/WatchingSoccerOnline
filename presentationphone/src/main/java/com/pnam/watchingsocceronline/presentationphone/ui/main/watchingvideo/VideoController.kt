package com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo

import android.view.View
import com.pnam.watchingsocceronline.presentationphone.databinding.ControllerVideoBinding

class VideoController(
    controller: View
) {
    init {
        onCreateView(controller)
        onViewCreated()
    }

    private var _binding: ControllerVideoBinding? = null
    private val binding: ControllerVideoBinding get() = _binding!!
    private fun onCreateView(View: View) {
        _binding = ControllerVideoBinding.bind(View)
    }

    private fun onViewCreated() {
        binding.apply {
            fullScreen.setOnClickListener {
                fullScreenEvent?.invoke(isFullScreen)
                isFullScreen = !isFullScreen
            }
            collapse.setOnClickListener {
                collapseEvent?.invoke()
            }
        }
    }

    private var isFullScreen: Boolean = false

    internal var fullScreenEvent: ((isFullScreen: Boolean) -> Unit)? = null
    internal var collapseEvent: (() -> Unit)? = null
}