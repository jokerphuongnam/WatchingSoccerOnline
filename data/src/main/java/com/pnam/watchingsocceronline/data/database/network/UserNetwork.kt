package com.pnam.watchingsocceronline.data.database.network

import com.pnam.watchingsocceronline.data.throwable.NotFoundException
import com.pnam.watchingsocceronline.data.throwable.WrongException
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface UserNetwork {
    @Throws(NotFoundException::class)
    suspend fun login(email: String, password: String): User
    suspend fun editUser(user: User)

    @Throws(WrongException::class)
    suspend fun register(user: User)
}