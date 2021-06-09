package com.pnam.watchingsocceronline.data.database.network.dto

import com.pnam.watchingsocceronline.domain.model.Comment

data class CommentDto(
    var videoId: String,
    var comment: String
) {
    fun toComment(comment: Comment){

    }
}