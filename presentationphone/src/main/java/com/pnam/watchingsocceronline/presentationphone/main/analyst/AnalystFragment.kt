package com.pnam.watchingsocceronline.presentationphone.main.analyst

import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.presentationphone.main.MainFragment

class AnalystFragment : MainFragment<AnalystViewModel>() {

    override val viewModel: AnalystViewModel by viewModels()

    override fun createView() {

    }

    override fun onResume() {
        super.onResume()
        actionBar.title = "Analyst"
    }
}