package com.pnam.watchingsocceronline.presentationphone.usecase.impl

import android.graphics.Bitmap
import com.pnam.watchingsocceronline.data.repository.UserRepository
import com.pnam.watchingsocceronline.presentationphone.usecase.OptionsAvatarUseCase
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class DefaultOptionsAvatarUseCaseImpl @Inject constructor(
    override val userRepository: UserRepository
) : OptionsAvatarUseCase {
    override suspend fun deleteAvatar(uid: String) {
        userRepository.removeAvatar(uid)
    }
}