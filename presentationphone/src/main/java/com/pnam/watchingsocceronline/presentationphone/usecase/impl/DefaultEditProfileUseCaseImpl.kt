package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.presentationphone.usecase.EditProfileUseCase
import javax.inject.Inject

class DefaultEditProfileUseCaseImpl @Inject constructor(
    override val userRepository: UserRepository
) : EditProfileUseCase {
    override suspend fun edit(user: User) {
        userRepository.edit(user)
    }
}