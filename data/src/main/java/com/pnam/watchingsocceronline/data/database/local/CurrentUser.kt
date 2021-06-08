package com.pnam.watchingsocceronline.data.database.local

import javax.inject.Singleton

@Singleton
interface CurrentUser {
    suspend fun findUid(): String?
    suspend fun changeCurrentUser(uid: String)
    suspend fun signOut()
}