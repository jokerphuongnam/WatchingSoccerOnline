package com.pnam.watchingsocceronline.presentationphone.ui.main.library

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentLibraryBinding

class LibraryFragment :
    BaseFragment<FragmentLibraryBinding, LibraryViewModel>(R.layout.fragment_library) {
    override val viewModel: LibraryViewModel by viewModels()

    override fun createView() {

    }

    override fun onResume() {
        super.onResume()
        actionBar.title = "Library"
    }
}