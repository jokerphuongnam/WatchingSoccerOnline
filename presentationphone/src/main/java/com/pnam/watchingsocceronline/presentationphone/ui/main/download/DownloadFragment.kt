package com.pnam.watchingsocceronline.presentationphone.ui.main.download

import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.databinding.FragmentDownloadBinding
import com.pnam.watchingsocceronline.presentationphone.ui.base.BaseFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.MainViewModel
import com.pnam.watchingsocceronline.presentationphone.utils.ContainerItemCallback
import com.pnam.watchingsocceronline.presentationphone.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class DownloadFragment :
    BaseFragment<FragmentDownloadBinding, DownloadViewModel, MainViewModel>(R.layout.fragment_download) {
    override val activityViewModel: MainViewModel by activityViewModels()
    override val viewModel: DownloadViewModel by viewModels()

    private val downloadsAdapter: DownloadsAdapter by lazy {
        DownloadsAdapter(itemCallback, downloadOptionMenuCallback)
    }

    private val itemCallback: ContainerItemCallback<Download> by lazy {
        object : ContainerItemCallback<Download> {
            override fun onLongTouch(data: Download) {
            }

            override fun onClick(data: Download) {
                openVideoBottomSheet.invoke(data.vid)
            }
        }
    }

    private val downloadOptionMenuCallback: DownloadsAdapter.DownloadOptionMenuCallback by lazy {
        object : DownloadsAdapter.DownloadOptionMenuCallback {
            override fun removeDownload(video: Download) {
                viewModel.removeDownload(video)
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
                    downloadsAdapter.submitList(it.data!!.toMutableList())
                }
                is Resource.Error -> {
                    binding.loading.isVisible = false
                }
            }
        }
        viewModel.removeDownload.observe {
            when (it) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {
                    showToast(R.string.delete_download_success)
                }
                is Resource.Success -> {
                    showToast(R.string.delete_download_error)
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

    private fun setUpRecyclerView() {
        binding.downloads.adapter = downloadsAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                removeThisFragmentFromBackStack()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView() {
        setViewModelObserve()
        setUpAppbar()
        setUpRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        resultReceiverCallBack.setFragment(this)
        viewModel.getVideoDownloads()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeThisFragmentFromBackStack()
    }

    internal lateinit var resultReceiverCallBack: DownloadResultReceiverCallback

    internal lateinit var openVideoBottomSheet: (String) -> Unit
}