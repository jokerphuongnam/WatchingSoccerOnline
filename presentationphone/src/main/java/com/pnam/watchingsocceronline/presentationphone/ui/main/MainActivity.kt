package com.pnam.watchingsocceronline.presentationphone.ui.main

import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityMainBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.ui.main.chart.ChartFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.container.ContainerFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.home.HomeFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.library.LibraryFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo.WatchVideoBottomSheet
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    private val containerId: Int by lazy {
        R.id.container
    }

    private val mainFragments: List<Fragment> by lazy {
        mutableListOf(
            homeFragment,
            chartFragment,
            libraryFragment
        )
    }

    private val homeFragment: HomeFragment by lazy {
        HomeFragment().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
        }
    }

    private val chartFragment: ChartFragment by lazy {
        ChartFragment().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
        }
    }

    private val libraryFragment: LibraryFragment by lazy {
        LibraryFragment().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
        }
    }

    private val watchVideoBottomSheet: WatchVideoBottomSheet by lazy {
        WatchVideoBottomSheet(
            this,
            viewModel,
            binding.watchingVideo,
            paddingBottom
        ).apply {
            motionProgressChanged = this@MainActivity.motionProgressChanged
        }
    }

    private val onNavigationItemSelectedListener: BottomNavigationView.OnNavigationItemSelectedListener by lazy {
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.home -> {
                    mainFragments[0]
                }
                R.id.chart -> {
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
        binding.bottomNavigation.setOnNavigationItemSelectedListener(
            onNavigationItemSelectedListener
        )
    }

    private var paddingBottom: Int = 0
    private var bottomSheetOutSideScreen: Float = 0.0f

    private fun setUpRoomDistance() {
        paddingBottom = binding.container.paddingBottom
    }

    override fun onCreateView() {
        setUpBottomNavigation()
        setUpRoomDistance()
        showFragment(mainFragments[0])
        watchVideoBottomSheet.onInit()
    }

    private fun showFragment(
        fragment: Fragment,
        transactionViews: List<View>? = null
    ) {
        showFragment(containerId, fragment, transactionViews)
    }

    override val viewModel: MainViewModel by viewModels()

    private val openVideoBottomSheet: (Long) -> Unit by lazy {
        { vid ->
            bottomSheetOutSideScreen = binding.bottomNavigation.y + paddingBottom
            binding.bottomNavigation.y += bottomSheetOutSideScreen
            watchVideoBottomSheet.show()
            viewModel.openVideo(vid)
        }
    }

    private val motionProgressChanged: (Float) -> Unit by lazy {
        { progress ->
            val bottomNavigationPos = progress * paddingBottom.toFloat()
            binding.container.setPadding(0, 0, 0, bottomNavigationPos.toInt())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        supportFragmentManager.findFragmentById(containerId)?.also {
            (it as ContainerFragment<*>).onCreateOptionsMenu(menu)
        }
        return super.onCreateOptionsMenu(menu)
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