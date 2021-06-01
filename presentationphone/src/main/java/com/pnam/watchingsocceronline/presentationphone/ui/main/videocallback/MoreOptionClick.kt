package com.pnam.watchingsocceronline.presentationphone.ui.main.videocallback

import com.pnam.watchingsocceronline.domain.model.Video

internal interface MoreOptionClick {
    fun download(video: Video)
    fun getNotification(video: Video)
    fun removeFromHistory(video: Video)
}