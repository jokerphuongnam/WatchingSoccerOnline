package com.pnam.watchingsocceronline.presentationphone.ui.main

import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnam.watchingsocceronline.model.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityMainBinding
import com.pnam.watchingsocceronline.presentationphone.ui.main.analyst.AnalystFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.home.HomeFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.library.LibraryFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.watchingvideo.WatchVideoBottomSheet

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    private val mainFragments: List<Fragment> by lazy {
        mutableListOf(
            HomeFragment().apply {
                openVideoBottomSheet = this@MainActivity.openVideoBottomSheet
            },
            AnalystFragment(),
            LibraryFragment()
        )
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
            if (fragment === supportFragmentManager.findFragmentById(R.id.container)) {
                false
            } else {
                showFragment(fragment, fragment.javaClass.simpleName)
                true
            }
        }
    }

    private fun setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


    override fun createView() {
        setUpBottomNavigation()
        showFragment(mainFragments[0], mainFragments[0].javaClass.simpleName)
    }

    private fun showFragment(
        fragment: Fragment,
        tag: String,
        transactionViews: List<View>? = null
    ) {
        showFragment(R.id.container, fragment, tag, transactionViews)
    }

    override val viewModel: MainViewModel by viewModels()

    private val openVideoBottomSheet: (Video) -> Unit by lazy {
        {
            WatchVideoBottomSheet(supportFragmentManager.beginTransaction(), it).show()
        }
    }
}