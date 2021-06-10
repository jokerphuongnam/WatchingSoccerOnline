package com.pnam.watchingsocceronline.presentationphone.background

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pnam.watchingsocceronline.data.database.local.DownloadLocal
import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.data.utils.toDownload
import com.pnam.watchingsocceronline.domain.model.Video
import com.pnam.watchingsocceronline.presentationphone.R
import com.pnam.watchingsocceronline.presentationphone.background.DownloadVideoReceiver.Companion.PARAM_RESULT
import com.pnam.watchingsocceronline.presentationphone.background.DownloadVideoReceiver.Companion.RESULT_CODE_OK
import com.pnam.watchingsocceronline.presentationphone.ui.main.download.DownloadFragment
import com.pnam.watchingsocceronline.presentationphone.ui.main.download.DownloadResultReceiverCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@FlowPreview
@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class DownloadVideoService : IntentService("DownloadVideoService") {
    @Inject
    lateinit var downloadVideo: DownloadVideo

    @Inject
    lateinit var videoLocal: DownloadLocal

    internal enum class Params {
        VIDEO, RESULT_RECEIVER
    }

    internal fun setFragmentForResultReceiverCallback(fragment: DownloadFragment) {
        (resultReceiver as DownloadVideoReceiver).setFragment(fragment)
    }

    private lateinit var resultReceiver: ResultReceiver

    private val notificationBuilder: NotificationCompat.Builder by lazy {
        NotificationCompat.Builder(applicationContext, DOWNLOAD_CHANNEL).apply {
            setDefaults(NotificationCompat.DEFAULT_ALL)
            setAutoCancel(false)
            setSmallIcon(R.drawable.ic_download)
            priority = NotificationCompat.PRIORITY_MAX
        }
    }

    override fun onHandleIntent(intent: Intent?) {
        val video: Video = intent?.getParcelableExtra(Params.VIDEO.name)!!
        resultReceiver = intent.getParcelableExtra(Params.RESULT_RECEIVER.name)!!
        val download = video.toDownload()

        downloadVideo.downloadVideo(video, object : DownloadVideo.DownloadVideoCallback {
            override fun uri(uri: Uri) {
                download.url = uri.toString()
                try {
                    videoLocal.insertDownloadNoneSuspend(download)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun process(process: Int) {
                notificationBuilder.apply {
                    setProgress(100, process, false)
                    setContentTitle(video.title)
                }
                download.downloadProcess = process
                NotificationManagerCompat.from(applicationContext)
                    .notify(DOWNLOAD_NOTIFICATION_ID, notificationBuilder.build())
                resultReceiver.send(RESULT_CODE_OK, Bundle().apply {
                    putParcelable(PARAM_RESULT, download)
                })
                if (download.downloadProcess == 100) {
                    videoLocal.editDownloadNoneSuspend(download)
                }
            }
        })
    }

    companion object {
        const val DOWNLOAD_CHANNEL = "DownloadChannel"
        const val DOWNLOAD_NOTIFICATION_ID: Int = 22231

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