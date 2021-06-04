package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.CommentsRepository
import com.pnam.watchingsocceronline.presentationphone.usecase.WriteCommentUseCase
import javax.inject.Inject

class DefaultWriteCommentUseCaseImpl @Inject constructor(
    override val commentRepository: CommentsRepository
) : WriteCommentUseCase {
    override suspend fun writeComment(comment: String, vid: Long, uid: Long?) {
        commentRepository.writeComment(comment, vid, uid)
    }
}