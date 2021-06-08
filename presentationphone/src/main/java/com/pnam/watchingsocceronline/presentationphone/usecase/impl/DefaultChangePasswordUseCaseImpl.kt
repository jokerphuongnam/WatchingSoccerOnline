package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.usecase.ChangePasswordUseCase
import javax.inject.Inject

class DefaultChangePasswordUseCaseImpl @Inject constructor(
    override val userRepository: UserRepository
) : ChangePasswordUseCase {

    @Throws(NotFoundException::class)
    override suspend fun getUser(uid: String): User {
        return userRepository.getUser(uid)
    }

    override suspend fun changePassword(user: User) {
        userRepository.changePassword(user)
    }
}