package com.pnam.watchingsocceronline.data.database.local

import com.pnam.watchingsocceronline.domain.model.Download
import kotlinx.coroutines.flow.Flow

interface DownloadLocal {
    fun getDownloads(): Flow<Download>

    suspend fun getDownload(vid: String): Download

    suspend fun insertDownload(download: Download)

    suspend fun deleteDownload(download: Download)

    suspend fun editDownload(download: Download)
}