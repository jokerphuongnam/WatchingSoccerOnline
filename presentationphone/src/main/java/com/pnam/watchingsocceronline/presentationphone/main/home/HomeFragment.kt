package com.pnam.watchingsocceronline.presentationphone.main.home

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {
    override fun createView() {

    }

    override val viewModel: HomeViewModel by viewModels()
}