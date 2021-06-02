package com.pnam.watchingsocceronline.presentationphone.background

import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.presentationphone.ui.main.download.DownloadFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.download.DownloadResultReceiverCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@FlowPreview
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class DownloadVideoReceiver(
    handler: Handler,
    private val receiver: DownloadResultReceiverCallback
) : ResultReceiver(handler) {

    internal fun setFragment(fragment: DownloadFragment) {
        receiver.setFragment(fragment)
    }

    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
        super.onReceiveResult(resultCode, resultData)
        if (resultCode == RESULT_CODE_OK) {
            val download: Download = resultData.getParcelable(PARAM_RESULT)!!
            receiver.onSuccess(download)
        }
    }

    companion object {
        const val RESULT_CODE_OK: Int = 1245
        const val RESULT_CODE_ERROR: Int = 666
        const val PARAM_RESULT: String = "result"
    }
}