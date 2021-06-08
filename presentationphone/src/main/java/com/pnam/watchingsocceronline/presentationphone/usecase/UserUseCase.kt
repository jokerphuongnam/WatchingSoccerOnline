package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface UserUseCase {
    val userRepository: UserRepository
    suspend fun getUser(): User
    suspend fun signOut()
}