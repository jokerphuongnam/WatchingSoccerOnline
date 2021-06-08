package com.pnam.watchingsocceronline.data.database.local

import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface UserLocal {
    suspend fun findUsers(): List<User>
    suspend fun findUser(uid: String): User
    suspend fun insertUser(user: User)
    suspend fun editUser(user: User)
    suspend fun deleteUser(user: User)
}