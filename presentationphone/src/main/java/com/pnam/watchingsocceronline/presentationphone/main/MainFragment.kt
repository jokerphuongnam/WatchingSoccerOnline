package com.pnam.watchingsocceronline.presentationphone.main

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentMainBinding

abstract class MainFragment<VM : BaseViewModel> :
    BaseFragment<FragmentMainBinding, VM>(R.layout.fragment_main) {
    private var _actionbar: ActionBar? = null

    override val actionBar: ActionBar
        get() = _actionBar ?: synchronized(this) {
            _actionBar ?: (requireActivity() as AppCompatActivity).supportActionBar.also {
                _actionBar = it
            }!!
        }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).let {
            it.setSupportActionBar(binding.toolbar)
            _actionbar = it.supportActionBar
        }
    }
}