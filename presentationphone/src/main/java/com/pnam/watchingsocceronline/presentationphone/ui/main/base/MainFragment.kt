package com.pnam.watchingsocceronline.presentationphone.ui.main.base

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseViewModel
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentMainBinding
import com.pnam.watchingsocceronline.presentationphone.ui.main.toolbar.MainToolbar

abstract class MainFragment<VM : BaseViewModel> :
    BaseFragment<FragmentMainBinding, VM>(R.layout.fragment_main) {
    private var _actionbar: ActionBar? = null

    private val toolbar: MainToolbar by lazy {
        MainToolbar(requireActivity() as AppCompatActivity, binding.toolbar)
    }

    protected open fun setUpRecycler() {
        binding.videos.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
    }

    override val actionBar: ActionBar
        get() = _actionBar ?: synchronized(this) {
            _actionBar ?: (requireActivity() as AppCompatActivity).supportActionBar.also {
                _actionBar = it
            }!!
        }

    override fun onResume() {
        super.onResume()
        toolbar.onResume()
    }

    override fun createView() {
        setUpRecycler()
        toolbar.onCreate()
    }
}