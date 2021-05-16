package com.pnam.watchingsocceronline.presentationphone.main

import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.base.BaseActivity
import com.pnam.watchingsocceronline.presentationphone.databinding.ActivityMainBinding
import com.pnam.watchingsocceronline.presentationphone.main.analyst.AnalystFragment
import com.pnam.watchingsocceronline.presentationphone.main.home.HomeFragment
import com.pnam.watchingsocceronline.presentationphone.main.setting.SettingFragment

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private val mainContainerAdapter: MainContainerAdapter by lazy {
        MainContainerAdapter(this).apply {
            addFragment(HomeFragment())
            addFragment(AnalystFragment())
            addFragment(SettingFragment())
        }
    }

    private val bottomNavigationView: BottomNavigationView get() = binding.bottomNavigation

    private val container: ViewPager2 get() = binding.container

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.home -> {
                container.setCurrentItem(0, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.analyst -> {
                container.setCurrentItem(1, true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.setting -> {
                container.setCurrentItem(2, true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setUpViewPager(){
        container.apply {
            currentItem = 0
            adapter = mainContainerAdapter
            setPageTransformer { _, _ -> alpha = 1f }
        }
    }

    private fun setUpBottomNavigation(){
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }


    override fun createView() {
        setUpViewPager()
        setUpBottomNavigation()
    }

    override val viewModel: MainViewModel by viewModels()
}