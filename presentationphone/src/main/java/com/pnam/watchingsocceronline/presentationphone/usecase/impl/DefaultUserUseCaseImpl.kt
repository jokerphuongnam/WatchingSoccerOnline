package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.usecase.UserUseCase
import javax.inject.Inject

class DefaultUserUseCaseImpl @Inject constructor(
    override val userRepository: UserRepository
) : UserUseCase {

    override suspend fun getUser(): User = userRepository.getUser()

    override suspend fun signOut() {
        userRepository.signOut()
    }
}