package com.pnam.watchingsocceronline.presentationphone.ui.main.analyst

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.ui.main.base.MainFragment

class AnalystFragment : MainFragment<AnalystViewModel>() {

    override val viewModel: AnalystViewModel by viewModels()

    override fun createView() {
        super.createView()
    }

    override fun onResume() {
        super.onResume()
        actionBar.title = "Analyst"
    }
}