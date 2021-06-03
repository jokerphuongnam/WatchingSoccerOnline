package com.pnam.watchingsocceronline.data.database.network

import android.content.res.Resources
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Singleton

@Singleton
interface UserNetwork {
    @Throws(Resources.NotFoundException::class)
    suspend fun login(email: String, password: String): User
    suspend fun editUser(user: User)
}