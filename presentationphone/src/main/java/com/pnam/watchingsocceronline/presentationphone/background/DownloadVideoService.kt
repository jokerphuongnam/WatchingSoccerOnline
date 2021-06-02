package com.pnam.watchingsocceronline.presentationphone.background

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.utils.toDownload
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.background.DownloadVideoReceiver.Companion.PARAM_RESULT
import com.pnam.watchingsocceronline.presentationphone.background.DownloadVideoReceiver.Companion.RESULT_CODE_OK
import com.pnam.watchingsocceronline.presentationphone.ui.main.download.DownloadFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.download.DownloadResultReceiverCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@FlowPreview
@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class DownloadVideoService : BaseIntentService("DownloadVideoService") {
    @Inject
    lateinit var downloadVideo: DownloadVideo

    internal enum class Params {
        VIDEO, RESULT_RECEIVER
    }

    internal fun setFragmentForResultReceiverCallback(fragment: DownloadFragment) {
        (resultReceiver as DownloadVideoReceiver).setFragment(fragment)
    }

    private lateinit var resultReceiver: ResultReceiver

    override fun onHandleIntent(intent: Intent?) {
        val video: Video = intent?.getParcelableExtra(Params.VIDEO.name)!!
        resultReceiver = intent.getParcelableExtra(Params.RESULT_RECEIVER.name)!!
        val download = video.toDownload()

        serviceScope.launch(Dispatchers.IO) {
            downloadVideo.downloadVideo(video) { uri ->
                download.url = uri.toString()
            }.collect { process ->
                resultReceiver.send(RESULT_CODE_OK, Bundle().apply {
                    putParcelable(PARAM_RESULT, video.toDownload().apply {
                        downloadProcess = process
                    })
                })
            }
        }
    }

    companion object {
        fun startServiceToWithdraw(
            context: Context,
            video: Video,
            resultReceiverCallBack: DownloadResultReceiverCallback
        ) {
            val downloadVideoReceiver =
                DownloadVideoReceiver(Handler(context.mainLooper), resultReceiverCallBack)

            val intent = Intent(context, DownloadVideoService::class.java)
            intent.putExtra(Params.VIDEO.name, video)
            intent.putExtra(Params.RESULT_RECEIVER.name, downloadVideoReceiver)
            context.startService(intent)
        }
    }
}