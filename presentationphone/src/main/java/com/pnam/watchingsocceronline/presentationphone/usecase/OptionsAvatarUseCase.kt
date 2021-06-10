package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.UserRepository
import javax.inject.Singleton

@Singleton
interface OptionsAvatarUseCase {
    val userRepository: UserRepository

    suspend fun deleteAvatar(uid: String)
}