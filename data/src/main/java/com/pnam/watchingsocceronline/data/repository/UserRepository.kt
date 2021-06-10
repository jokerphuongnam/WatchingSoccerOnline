package com.pnam.watchingsocceronline.data.repository

import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.database.local.UserLocal
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.data.throwable.EmailInvalid
import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface UserRepository {
    val currentUser: CurrentUser
    val userLocal: UserLocal
    val userNetwork: UserNetwork

    suspend fun getUid(): String?

    @Throws(NotFoundException::class)
    suspend fun getUser(): User

    @Throws(NotFoundException::class)
    suspend fun getUser(uid: String): User
    suspend fun login(email: String, password: String): User
    suspend fun saveUser(user: User)

    @Throws(NotFoundException::class, EmailInvalid::class)
    suspend fun register(user: User)

    @Throws(NotFoundException::class)
    suspend fun edit(user: User)

    suspend fun uploadAvatar(uid: String, avatar: ByteArray)

    suspend fun removeAvatar(uid: String)

    @Throws(NotFoundException::class)
    suspend fun changePassword(user: User)
    suspend fun signOut()
}