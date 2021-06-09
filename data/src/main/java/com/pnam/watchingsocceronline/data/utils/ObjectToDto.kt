package com.pnam.watchingsocceronline.data.utils

import com.pnam.watchingsocceronline.data.database.local.dto.DownloadDto
import com.pnam.watchingsocceronline.data.database.local.dto.NotificationDto
import com.pnam.watchingsocceronline.data.database.local.dto.UserDto
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.DISLIKE
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.LIKE
import com.pnam.watchingsocceronline.domain.model.Download
import com.pnam.watchingsocceronline.domain.model.Notification
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video

fun Download.toDto(): DownloadDto =
    DownloadDto(vid, title, thumbnail, url, view, downloadTime, downloadProcess)

fun Download.toVideo(video: Video): Video = Video(
    vid,
    title,
    thumbnail,
    url,
    video.view,
    video.likes,
    video.dislikes,
    video.date,
    video.highLight1,
    video.highLight2,
    video.highLight3,
    video.comments,
    video.reactVideo
)

fun String.toReactVideo(): Video.ReactVideo = if (equals(LIKE, false)) {
    Video.ReactVideo.LIKE
} else if (equals(DISLIKE, false)) {
    Video.ReactVideo.DISLIKE
} else {
    Video.ReactVideo.NONE
}

fun Video.toDownload(): Download = Download(
    vid,
    title,
    thumbnail,
    "",
    view,
    System.currentTimeMillis(),
    0
)

fun User.toDto(): UserDto = UserDto(
    uid,
    avatar,
    email,
    password,
    firstName,
    lastName,
    birthDay,
    gender.toBoolean()
)

fun Video.toNotificationDto(): NotificationDto = NotificationDto(
    vid,
    title,
    thumbnail,
    System.currentTimeMillis(),
    date
)

fun Notification.toDto(): NotificationDto = NotificationDto(
    nid,
    title,
    thumbnail,
    getTime,
    notificationTime
)