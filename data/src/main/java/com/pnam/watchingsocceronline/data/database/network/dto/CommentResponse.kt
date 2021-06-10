package com.pnam.watchingsocceronline.data.database.network.dto

import com.pnam.watchingsocceronline.data.utils.RetrofitUtils
import com.pnam.watchingsocceronline.domain.model.Comment

class CommentResponse(
    var videoId: String,
    var comment: String,
    var avatar: String,
    var firstName: String,
    var lastName: String
) {
    fun toComment(): Comment {
        return Comment(
            videoId,
            comment,
            System.currentTimeMillis(),
            RetrofitUtils.convertLocalAvatar(avatar),
            firstName,
            lastName
        )
    }
}