package com.pnam.watchingsocceronline.data.database.local

import com.pnam.watchingsocceronline.domain.model.Download
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface DownloadLocal {
    fun findDownloads(): Flow<List<Download>>
    suspend fun findDownload(vid: String): Download
    suspend fun insertDownload(download: Download)
    fun insertDownloadNoneSuspend(download: Download)
    suspend fun deleteDownload(download: Download)
    suspend fun editDownload(download: Download)
    fun editDownloadNoneSuspend(download: Download)
}