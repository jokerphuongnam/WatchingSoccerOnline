package com.pnam.watchingsocceronline.data.database.network.dto

import com.pnam.watchingsocceronline.domain.model.Video

data class VideoResponse(
    var id: String,
    var title: String,
    var thumbnail: String,
    var url: String,
    var views: Long,
    var likes: Long,
    var dislikes: Long,
    var date: Long,
    var highLight1: String,
    var highLight2: String,
    var highLight3: String
) {
    fun toVideo(): Video {
        return Video(
            id,
            title,
            thumbnail,
            url,
            views,
            likes,
            dislikes,
            date,
            highLight1,
            highLight2,
            highLight3,
            mutableListOf()
        )
    }
}