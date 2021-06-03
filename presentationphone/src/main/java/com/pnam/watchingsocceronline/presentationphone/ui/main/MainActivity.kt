package com.pnam.watchingsocceronline.presentationphone.ui.main

import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityMainBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.ui.main.chart.ChartFragmentMain
import com.pnam.watchingsocceronline.presentationphone.ui.main.download.DownloadFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.home.HomeFragmentMain
import com.pnam.watchingsocceronline.presentationphone.ui.main.library.LibraryFragmentMain
import com.pnam.watchingsocceronline.presentationphone.ui.main.maincontainer.MainContainerFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo.WatchVideoBottomSheet
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@AndroidEntryPoint
@InternalCoroutinesApi
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

    private val homeFragment: HomeFragmentMain by lazy {
        HomeFragmentMain().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
        }
    }

    private val chartFragment: ChartFragmentMain by lazy {
        ChartFragmentMain().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
        }
    }

    private val libraryFragment: LibraryFragmentMain by lazy {
        LibraryFragmentMain().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
            openDownloadFragment = {
                showFragment(downloadFragment)
            }
        }
    }

    private val downloadFragment: DownloadFragment
        get() = DownloadFragment().apply {
            openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
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
            supportFragmentManager.findFragmentById(R.id.container)
                .takeIf { it is DownloadFragment }?.let {
                    supportFragmentManager.commit {
                        remove(it)
                    }
                    supportFragmentManager.popBackStack()
                }
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

    private fun showFragment(
        fragment: Fragment,
        transactionViews: List<View>? = null
    ) {
        showFragment(containerId, fragment, transactionViews)
    }

    override val viewModel: MainViewModel by viewModels()

    private val openVideoBottomSheet: (Long) -> Unit by lazy {
        { vid ->
            (binding.bottomNavigation.y + paddingBottom).let {
                bottomSheetOutSideScreen = it
                binding.bottomNavigation.y = it
            }
            watchVideoBottomSheet.show()
            viewModel.openVideo(vid)
        }
    }

    private val motionProgressChanged: (Float) -> Unit by lazy {
        { progress ->
            (progress * paddingBottom.toFloat()).let {
                binding.container.setPadding(0, 0, 0, it.toInt())
                binding.bottomNavigation.y = bottomSheetOutSideScreen - it
            }
        }
    }

    private fun setUpViewModel() {
        viewModel.userLiveData.observe {
            when (it) {
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    viewModel.userObservers.forEach { observers ->
                        it.data?.let { user -> observers.invoke(user) }
                    }
                }
                is Resource.Error -> {

                }
            }
        }
        viewModel.user()
    }

    override fun onCreateView() {
        setUpBottomNavigation()
        setUpRoomDistance()
        setUpViewModel()
        showFragment(mainFragments[0])
        watchVideoBottomSheet.onInit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        supportFragmentManager.findFragmentById(containerId)?.also {
            if (it is MainContainerFragment<*>) it.onCreateOptionsMenu(menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        supportFragmentManager.findFragmentById(containerId)?.also {
            if (it is DownloadFragment) it.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (watchVideoBottomSheet.isShow) {
            if (watchVideoBottomSheet.collapseEvent()) {
                super.onBackPressed()
            } else {
                watchVideoBottomSheet.collapseEvent()
            }
        } else {
            val fragment: Fragment? = supportFragmentManager.findFragmentById(R.id.container)
            if (fragment != null && fragment is DownloadFragment) {
                showFragment(mainFragments[2])
            } else {
                super.onBackPressed()
            }
        }
    }
}