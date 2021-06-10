package com.pnam.watchingsocceronline.data.repository.impl

import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.repository.CommentsRepository
import com.pnam.watchingsocceronline.domain.model.Comment
import javax.inject.Inject

class DefaultCommentsRepositoryImpl @Inject constructor(
    override val videoNetwork: VideoNetwork
) : CommentsRepository {
    override suspend fun getComments(vid: String): List<Comment> {
        return videoNetwork.fetchComments(vid)
    }

    override suspend fun writeComment(comment: String, vid: String, uid: String) {
        videoNetwork.writeComment(comment, vid, uid)
    }
}