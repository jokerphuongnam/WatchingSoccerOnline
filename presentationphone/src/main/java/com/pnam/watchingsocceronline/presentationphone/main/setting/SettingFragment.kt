package com.pnam.watchingsocceronline.presentationphone.main.setting

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentSettingBinding

class SettingFragment: BaseFragment<FragmentSettingBinding, SettingViewModel>(R.layout.fragment_setting) {
    override fun createView() {

    }

    override val viewModel: SettingViewModel by viewModels()
}