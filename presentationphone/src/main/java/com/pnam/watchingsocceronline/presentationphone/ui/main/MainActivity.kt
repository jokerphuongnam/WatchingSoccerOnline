package com.pnam.watchingsocceronline.presentationphone.ui.main

import android.content.res.Resources
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityMainBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.ui.main.analyst.AnalystFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.home.HomeFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.library.LibraryFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo.WatchVideoBottomSheet

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    private val mainFragments: List<Fragment> by lazy {
        mutableListOf(
            homeFragment,
            analystFragment,
            libraryFragment
        )
    }

    private val homeFragment: HomeFragment by lazy {
        HomeFragment().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
        }
    }

    private val analystFragment: AnalystFragment by lazy {
        AnalystFragment().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
        }
    }

    private val libraryFragment: LibraryFragment by lazy {
        LibraryFragment()
    }

    private val watchVideoBottomSheet: WatchVideoBottomSheet by lazy {
        WatchVideoBottomSheet(
            this,
            viewModel,
            binding.watchingVideo,
            paddingBottom
        ).apply {
            collapseEvent = this@MainActivity.collapseEvent
            motionProgressChanged = this@MainActivity.motionProgressChanged
        }
    }

    private val bottomNavigationView: BottomNavigationView get() = binding.bottomNavigation

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener by lazy {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.home -> {
                    mainFragments[0]
                }
                R.id.analyst -> {
                    mainFragments[1]
                }
                R.id.library -> {
                    mainFragments[2]
                }
                else -> {
                    mainFragments[0]
                }
            }
            showFragment(fragment)
            true
        }
    }

    private fun setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private var paddingBottom: Int = 0

    override fun createView() {
        paddingBottom = binding.container.paddingBottom
        setUpBottomNavigation()
        showFragment(mainFragments[0])
        watchVideoBottomSheet.onInit()
    }

    private fun showFragment(
        fragment: Fragment,
        transactionViews: List<View>? = null
    ) {
        showFragment(R.id.container, fragment, transactionViews)
    }

    override val viewModel: MainViewModel by viewModels()

    private val openVideoBottomSheet: (Video) -> Unit by lazy {
        { video ->
            binding.container.setPadding(0, 0, 0, 0)
//            binding.bottomNavigation.y = Resources.getSystem().displayMetrics.heightPixels.toFloat()
            viewModel.videoLiveData.postValue(video)
            watchVideoBottomSheet.show()
        }
    }

    private val collapseEvent: (() -> Unit) by lazy {
        {
            binding.container.setPadding(0, 0, 0, paddingBottom)
        }
    }

    private val motionProgressChanged: (Float) -> Unit by lazy {
        { progress ->
            binding.container.setPadding(0, 0, 0, (progress * paddingBottom.toFloat()).toInt())
            binding.bottomNavigation.y =
                Resources.getSystem().displayMetrics.heightPixels.toFloat() - (progress * paddingBottom.toFloat()).toInt()
        }
    }

    override fun onBackPressed() {
        if (!watchVideoBottomSheet.isShow) {
            super.onBackPressed()
        } else {
            if (watchVideoBottomSheet.collapseEvent()) {
                super.onBackPressed()
            } else {
                watchVideoBottomSheet.collapseEvent()
            }
        }
    }
}