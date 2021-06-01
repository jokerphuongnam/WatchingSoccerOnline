package com.pnam.watchingsocceronline.presentationphone.background

import android.app.Notification
import android.content.Context
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters

class DownloadWorkManager(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result = try {
        setForegroundAsync(createForegroundInfo())
        Result.success()
    } catch (ex: Exception) {
        Result.failure()
    }

    private fun createForegroundInfo(): ForegroundInfo{
        return ForegroundInfo(12, Notification())
    }
}