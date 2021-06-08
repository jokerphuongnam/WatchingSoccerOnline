package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.usecase.RegisterUseCase
import javax.inject.Inject

class DefaultRegisterUseCaseImpl @Inject constructor(
    override val userRepository: UserRepository
) : RegisterUseCase {
    override suspend fun register(user: User) {
        userRepository.register(user)
    }
}