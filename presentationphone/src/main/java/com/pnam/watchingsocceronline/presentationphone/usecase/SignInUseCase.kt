package com.pnam.watchingsocceronline.presentationphone.usecase

import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface SignInUseCase {
    val userRepository: UserRepository
    suspend fun login(email: String, password: String): User
    suspend fun saveUser(user: User)
}