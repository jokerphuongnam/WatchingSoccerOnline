package com.pnam.watchingsocceronline.data.utils

import com.pnam.watchingsocceronline.data.database.local.dto.DownloadDto
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Video

fun Download.toDto(): DownloadDto = DownloadDto(vid, title, thumbnail, url, view, downloadTime)

fun Download.toVideo(video: Video): Video = Video(
    vid,
    title,
    thumbnail,
    url,
    video.view,
    video.date,
    video.highLight1,
    video.highLight2,
    video.highLight3,
    video.comments
)

fun Video.toDownload(): Download = Download(
    vid,
    title,
    thumbnail,
    "",
    view,
    System.currentTimeMillis(),
    0
)