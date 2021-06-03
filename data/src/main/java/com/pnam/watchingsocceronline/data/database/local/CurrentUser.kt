package com.pnam.watchingsocceronline.data.database.local

import javax.inject.Singleton

@Singleton
interface CurrentUser {
    suspend fun findUid(): Long?
    suspend fun changeCurrentUser(uid: Long)
    suspend fun signOut()
}