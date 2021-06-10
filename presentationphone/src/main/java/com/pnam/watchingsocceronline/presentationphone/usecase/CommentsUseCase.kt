package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.CommentsRepository
import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.Comment
import javax.inject.Singleton

@Singleton
interface CommentsUseCase {
    val commentRepository: CommentsRepository
    val userRepository: UserRepository

    suspend fun getComments(vid: String): List<Comment>
}