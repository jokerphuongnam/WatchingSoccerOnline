package com.pnam.watchingsocceronline.data.database.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pnam.watchingsocceronline.domain.model.Download

@Entity(tableName = "downloads")
data class DownloadDto(
    @PrimaryKey
    @ColumnInfo(name = "video_id")
    var vid: Long,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "thumbnail")
    var thumbnail: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "view")
    var view: Long,
    @ColumnInfo(name = "download_time")
    var downloadTime: Long,
    @ColumnInfo(name = "download_process")
    var downloadProcess: Int
) {
    fun toDownload(): Download {
        return Download(vid, title, thumbnail, url, view, downloadTime, downloadProcess)
    }
}