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
    vid.trim(),
    title.trim(),
    thumbnail.trim(),
    url.trim(),
    video.view,
    video.likes,
    video.dislikes,
    video.date,
    video.highLight1.trim(),
    video.highLight2.trim(),
    video.highLight3.trim(),
    video.comments,
    video.reactVideo
)

fun String.toReactVideo(): Video.ReactVideo = when {
    equals(LIKE, false) -> {
        Video.ReactVideo.LIKE
    }
    equals(DISLIKE, false) -> {
        Video.ReactVideo.DISLIKE
    }
    else -> {
        Video.ReactVideo.NONE
    }
}

fun Video.toDownload(): Download = Download(
    vid.trim(),
    title.trim(),
    thumbnail.trim(),
    "",
    view,
    System.currentTimeMillis(),
    0
)

fun User.toDto(): UserDto = UserDto(
    uid.trim(),
    avatar.trim(),
    email.trim(),
    password.trim(),
    firstName.trim(),
    lastName.trim(),
    birthDay,
    gender.toBoolean()
)

fun Video.toNotificationDto(): NotificationDto = NotificationDto(
    vid.trim(),
    title.trim(),
    thumbnail.trim(),
    System.currentTimeMillis(),
    date
)

fun Notification.toDto(): NotificationDto = NotificationDto(
    nid.trim(),
    title.trim(),
    thumbnail.trim(),
    getTime,
    notificationTime
)