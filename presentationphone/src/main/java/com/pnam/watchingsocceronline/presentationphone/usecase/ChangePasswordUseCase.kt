package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface ChangePasswordUseCase {
    val userRepository: UserRepository

    @Throws(NotFoundException::class)
    suspend fun getUser(uid: String): User

    @Throws(NotFoundException::class)
    suspend fun changePassword(user: User)
}