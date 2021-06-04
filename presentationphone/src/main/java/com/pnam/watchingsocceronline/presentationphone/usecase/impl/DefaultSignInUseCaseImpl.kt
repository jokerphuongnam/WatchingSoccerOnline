package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.usecase.SignInUseCase
import javax.inject.Inject

class DefaultSignInUseCaseImpl @Inject constructor(
    override val userRepository: UserRepository
) : SignInUseCase {
    override suspend fun login(email: String, password: String): User {
        return userRepository.login(email, password)
    }

    override suspend fun saveUser(user: User) {
        userRepository.saveUser(user)
    }
}