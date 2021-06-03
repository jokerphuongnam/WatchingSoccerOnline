package com.pnam.watchingsocceronline.data.database.local.impl

import android.content.Context
import android.net.Uri
import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Video
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.net.URL
import java.net.URLConnection
import javax.inject.Inject

class DownloadManagerDownloadVideoImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DownloadVideo {
    private var _url: URL? = null
    private val url: URL get() = _url!!

    private var isDownloading: Boolean = false

    override fun downloadVideo(
        video: Video,
        callback: DownloadVideo.DownloadVideoCallback
    ) {
        _url = URL(video.url)
        val connection: URLConnection = url.openConnection()
        connection.connect()
        // this will be useful so that you can show a typical 0-100% progress bar
        val fileLength: Int = connection.contentLength
        // download the file
        val input: InputStream = BufferedInputStream(connection.getInputStream())
        val fileName: String = "${video.vid.toString()}.mp4"
        val output: OutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        val file = File(context.filesDir, fileName)
        val uri: Uri = Uri.fromFile(file)
        callback.uri(uri)
        val data = ByteArray(1024)
        var total: Long = 0
        var count: Int
        isDownloading = true
        while (true) {
            count = input.read(data)
            if (count == -1 || !isDownloading) {
                break
            }
            total += count
            //calculate current progress
            callback.process((total * 100 / fileLength).toInt())
            //save current data
            output.write(data, 0, count)
        }
        output.flush()
        output.close()
        input.close()
        callback.process(100)
    }

    override fun cancelDownload(id: Long) {
        isDownloading = false
    }

    override fun deleteVideo(video: Download) {
        isDownloading = false
        File(video.url).takeIf { it.exists() }?.delete()
    }
}