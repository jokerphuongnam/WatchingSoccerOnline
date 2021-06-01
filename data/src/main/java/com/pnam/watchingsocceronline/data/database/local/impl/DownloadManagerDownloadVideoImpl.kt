package com.pnam.watchingsocceronline.data.database.local.impl

import android.app.DownloadManager
import android.content.Context
import com.pnam.watchingsocceronline.data.database.local.DownloadVideo
import com.pnam.watchingsocceronline.domain.model.Video
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.BufferedInputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL
import java.net.URLConnection
import javax.inject.Inject

class DownloadManagerDownloadVideoImpl @Inject constructor(
    private val downloadManager: DownloadManager,
    @ApplicationContext private val context: Context
) : DownloadVideo {
    private var _url: URL? = null
    private val url: URL get() = _url!!

    override fun downloadVideo(video: Video): Flow<Int> = flow {
        _url = URL(video.url)
        val connection: URLConnection = url.openConnection()
        connection.connect()
        // this will be useful so that you can show a typical 0-100% progress bar
        val fileLength: Int = connection.contentLength
        // download the file
        val input: InputStream = BufferedInputStream(connection.getInputStream())
        val output: OutputStream = context.openFileOutput(video.vid, Context.MODE_PRIVATE)
        val data = ByteArray(1024)
        var total: Long = 0
        var count: Int
        while (true) {
            count = input.read(data)
            if (count != -1) {
                break
            }
            total += count
            //calculate current progress
            emit((total * 100 / fileLength).toInt())
            //save current data
            output.write(data, 0, count)
        }
        output.flush()
        output.close()
        input.close()
        emit(100)
    }

    override fun cancelDownload(id: Long) {
        downloadManager.remove(id)
    }
}