package com.pnam.watchingsocceronline.data.database.local.impl

import androidx.room.*
import com.pnam.watchingsocceronline.data.database.local.DownloadLocal
import com.pnam.watchingsocceronline.data.database.local.dto.DownloadDto
import com.pnam.watchingsocceronline.data.utils.toDto
import com.pnam.watchingsocceronline.domain.model.Download
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface DownloadDao : DownloadLocal {
    @Query("SELECT * FROM DOWNLOADS")
    fun getDownloadsDto(): Flow<DownloadDto>

    override fun getDownloads(): Flow<Download> = getDownloadsDto().map {
        it.toDownload()
    }

    @Query("SELECT * FROM DOWNLOADS WHERE video_id = :vid")
    suspend fun getDownloadDto(vid: String): DownloadDto

    override suspend fun getDownload(vid: String): Download = getDownloadDto(vid).toDownload()

    @Insert
    suspend fun insertDownloadDto(download: DownloadDto)

    override suspend fun insertDownload(download: Download) {
        insertDownloadDto(download.toDto())
    }

    @Delete
    suspend fun deleteDownloadDto(download: DownloadDto)

    override suspend fun deleteDownload(download: Download) {
        deleteDownloadDto(download.toDto())
    }

    @Update
    suspend fun editDownloadDto(download: DownloadDto)

    override suspend fun editDownload(download: Download) {
        editDownloadDto(download.toDto())
    }
}