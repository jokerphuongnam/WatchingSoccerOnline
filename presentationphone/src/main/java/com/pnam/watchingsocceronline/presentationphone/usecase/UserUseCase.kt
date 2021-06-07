package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.UserRepository
import javax.inject.Singleton

@Singleton
interface UserUseCase {
    val userRepository: UserRepository
    suspend fun signOut()
}