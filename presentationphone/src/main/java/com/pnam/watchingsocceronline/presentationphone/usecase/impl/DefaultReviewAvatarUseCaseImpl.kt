package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import android.graphics.Bitmap
import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.presentationphone.usecase.ReviewAvatarUseCase
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class DefaultReviewAvatarUseCaseImpl @Inject constructor(
    override val userRepository: UserRepository
) : ReviewAvatarUseCase {
    override suspend fun changeAvatar(uid: String, avatar: Bitmap) {
        val byteArrayOutputStream = ByteArrayOutputStream()
        avatar.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        userRepository.uploadAvatar(uid, byteArrayOutputStream.toByteArray())
    }
}