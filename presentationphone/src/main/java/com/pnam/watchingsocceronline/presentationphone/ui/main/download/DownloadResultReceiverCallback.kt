package com.pnam.watchingsocceronline.presentationphone.ui.main.download

import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.presentationphone.utils.ResultReceiverCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import java.lang.ref.WeakReference

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class DownloadResultReceiverCallback : ResultReceiverCallback<Download> {
    internal fun setFragment(fragment: DownloadFragment) {
        weakFragment = WeakReference(fragment)
    }

    private var weakFragment: WeakReference<DownloadFragment>? = null

    override fun onSuccess(data: Download) {
        weakFragment?.get()?.setProgressCallback(data)
    }

    override fun onError(exception: Exception) {

    }
}