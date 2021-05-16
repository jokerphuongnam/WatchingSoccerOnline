package com.pnam.watchingsocceronline.presentationphone.main.home

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.main.MainFragment

class HomeFragment : MainFragment<HomeViewModel>() {
    override val viewModel: HomeViewModel by viewModels()

    override fun createView() {

    }

    override fun onResume() {
        super.onResume()
        actionBar.title = "Home"
    }
}