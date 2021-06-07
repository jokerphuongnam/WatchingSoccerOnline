package com.pnam.watchingsocceronline.data.repository

import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.database.local.UserLocal
import com.pnam.watchingsocceronline.data.database.network.UserNetwork
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface UserRepository {
    val currentUser: CurrentUser
    val userLocal: UserLocal
    val userNetwork: UserNetwork

    suspend fun getUid(): Long?
    suspend fun getUser(): User
    suspend fun login(email: String, password: String): User
    suspend fun saveUser(user: User)
    suspend fun edit(user: User)
    suspend fun signOut()
}