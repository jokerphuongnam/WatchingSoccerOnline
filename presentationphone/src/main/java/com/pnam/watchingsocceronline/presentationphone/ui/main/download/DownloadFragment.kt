package com.pnam.watchingsocceronline.presentationphone.ui.main.download

import android.app.ActivityManager
import android.app.Service
import android.content.Context
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.background.DownloadVideoService
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentDownloadBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.MainViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import com.pnam.watchingsocceronline.presentationphone.utils.ResultReceiverCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class DownloadFragment :
    BaseFragment<FragmentDownloadBinding, DownloadViewModel, MainViewModel>(R.layout.fragment_download) {
    override val activityViewModel: MainViewModel by activityViewModels()
    override val viewModel: DownloadViewModel by viewModels()

    private val downloadsAdapter: DownloadsAdapter by lazy {
        DownloadsAdapter(itemCallback)
    }

    private val itemCallback: ContainerItemCallback<Download> by lazy {
        object : ContainerItemCallback<Download> {
            override fun onLongTouch(data: Download) {
            }

            override fun onClick(data: Download) {
            }
        }
    }

    internal fun setProgressCallback(download: Download) {
        downloadsAdapter.processDownload(download)
    }

    private fun setViewModelObserve() {
        viewModel.videoDownloads.observe {
            when (it) {
                is Resource.Loading -> {
                    binding.loading.isVisible = true
                }
                is Resource.Success -> {
                    binding.loading.isVisible = false
//                    downloadsAdapter.submitList(it.data)
                }
                is Resource.Error -> {
                    binding.loading.isVisible = false
                }
            }
        }
    }

    private val appbar: ActionBar get() = (requireActivity() as AppCompatActivity).supportActionBar!!


    private fun setUpAppbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        appbar.title = "Downloads"
        appbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val fragmentManager = requireActivity().supportFragmentManager
                fragmentManager.commit {
                    remove(this@DownloadFragment)
                }
                fragmentManager.popBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inline fun <reified S : Service> getService(): S? {
        val activityManager: ActivityManager =
            requireContext().getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        @Suppress("DEPRECATION") val runningServices: List<ActivityManager.RunningServiceInfo> =
            activityManager.getRunningServices(Integer.MAX_VALUE)
        for (serviceRunning in runningServices) {
            if (serviceRunning is S) {
                return serviceRunning
            }
        }
        return null
    }

    private val resultReceiverCallback: DownloadResultReceiverCallback by lazy {
        DownloadResultReceiverCallback()
    }


    override fun onCreateView() {
        setViewModelObserve()
        setUpAppbar()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        viewModel.getVideoDownloads()
        val service: DownloadVideoService? = getService()
        service?.setFragmentForResultReceiverCallback(this)
    }
}