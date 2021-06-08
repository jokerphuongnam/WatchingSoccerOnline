package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface EditProfileUseCase {
    val userRepository: UserRepository

    suspend fun edit(user: User)
}