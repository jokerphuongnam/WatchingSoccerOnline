package com.pnam.watchingsocceronline.data.database.local.impl

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.pnam.watchingsocceronline.data.database.local.DownloadLocal
import com.pnam.watchingsocceronline.data.database.local.dto.DownloadDto
import com.pnam.watchingsocceronline.data.utils.toDto
import com.pnam.watchingsocceronline.domain.model.Download
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Dao
interface DownloadDao : DownloadLocal {
    @Query("SELECT * FROM DOWNLOADS")
    fun findDownloadsDto(): Flow<List<DownloadDto>>

    override fun findDownloads(): Flow<List<Download>> = findDownloadsDto().map { downloads ->
        downloads.map { download -> download.toDownload() }
    }

    @Query("SELECT * FROM DOWNLOADS WHERE video_id = :vid")
    suspend fun findDownloadDto(vid: String): DownloadDto

    override suspend fun findDownload(vid: String): Download = findDownloadDto(vid).toDownload()

    @Insert(onConflict = REPLACE)
    suspend fun insertDownloadDto(download: DownloadDto)

    override suspend fun insertDownload(download: Download) {
        insertDownloadDto(download.toDto())
    }

    @Insert(onConflict = REPLACE)
    fun insertDownloadDtoNoneSuspend(download: DownloadDto)

    override fun insertDownloadNoneSuspend(download: Download) {
        insertDownloadDtoNoneSuspend(download.toDto())
    }

    @Update
    suspend fun editDownloadDto(download: DownloadDto)

    override suspend fun editDownload(download: Download) {
        editDownloadDto(download.toDto())
    }

    @Update
    fun editDownloadDtoNoneSuspend(download: DownloadDto)

    override fun editDownloadNoneSuspend(download: Download) {
        editDownloadDtoNoneSuspend(download.toDto())
    }

    @Delete
    suspend fun deleteDownloadDto(download: DownloadDto)

    override suspend fun deleteDownload(download: Download) {
        deleteDownloadDto(download.toDto())
    }
}