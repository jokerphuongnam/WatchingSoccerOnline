package com.pnam.watchingsocceronline.presentationphone.main.setting

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.main.MainFragment

class SettingFragment :
    MainFragment<SettingViewModel>() {

    override val viewModel: SettingViewModel by viewModels()

    override fun createView() {

    }

    override fun onResume() {
        super.onResume()
        actionBar.title = "Resume"
    }
}