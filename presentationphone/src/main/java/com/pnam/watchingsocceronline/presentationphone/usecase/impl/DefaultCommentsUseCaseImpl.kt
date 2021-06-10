package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.CommentsRepository
import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.presentationphone.usecase.CommentsUseCase
import javax.inject.Inject

class DefaultCommentsUseCaseImpl @Inject constructor(
    override val commentRepository: CommentsRepository,
    override val userRepository: UserRepository
) : CommentsUseCase {
    override suspend fun getComments(vid: String): List<Comment> {
        return commentRepository.getComments(vid)
    }
}