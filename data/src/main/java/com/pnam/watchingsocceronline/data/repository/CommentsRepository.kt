package com.pnam.watchingsocceronline.data.repository

import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.domain.model.Comment
import javax.inject.Singleton

@Singleton
interface CommentsRepository {
    val videoNetwork: VideoNetwork
    suspend fun getComments(vid: String): List<Comment>
    suspend fun writeComment(comment: String, vid: String, uid: String)
}