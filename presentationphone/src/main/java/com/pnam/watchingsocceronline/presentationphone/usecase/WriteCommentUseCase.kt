package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.CommentsRepository
import javax.inject.Singleton

@Singleton
interface WriteCommentUseCase {
    val commentRepository: CommentsRepository

    suspend fun writeComment(comment: String, vid: String, uid: String)
}