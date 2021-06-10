package com.pnam.watchingsocceronline.data.database.network

import com.pnam.watchingsocceronline.data.throwable.EmailInvalid
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface UserNetwork {
    @Throws(NotFoundException::class)
    suspend fun login(email: String, password: String): User

    @Throws(NotFoundException::class)
    suspend fun editUser(user: User): User

    @Throws(NotFoundException::class)
    suspend fun changePassword(user: User): User

    @Throws(NotFoundException::class)
    suspend fun fetchUser(uid: String): User

    @Throws(NotFoundException::class, EmailInvalid::class)
    suspend fun register(user: User): User

    suspend fun uploadAvatar(uid: String, avatar: ByteArray): User

    suspend fun removeAvatar(uid: String): User
}