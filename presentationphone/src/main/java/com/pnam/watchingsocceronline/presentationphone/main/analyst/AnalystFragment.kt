package com.pnam.watchingsocceronline.presentationphone.main.analyst

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentAnalystBinding

class AnalystFragment: BaseFragment<FragmentAnalystBinding, AnalystViewModel>(R.layout.fragment_analyst) {
    override fun createView() {

    }

    override val viewModel: AnalystViewModel by viewModels()
}