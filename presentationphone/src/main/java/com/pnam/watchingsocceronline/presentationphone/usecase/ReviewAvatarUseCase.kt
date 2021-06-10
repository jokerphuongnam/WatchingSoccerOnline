package com.pnam.watchingsocceronline.presentationphone.usecase

import android.graphics.Bitmap
import com.pnam.watchingsocceronline.data.repository.UserRepository
import javax.inject.Singleton

@Singleton
interface ReviewAvatarUseCase {
    val userRepository: UserRepository

    suspend fun changeAvatar(uid: String, avatar: Bitmap)
}