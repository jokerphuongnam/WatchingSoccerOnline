package com.pnam.watchingsocceronline.data.database.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface CurrentUser {
    val uid: Flow<String?>
    suspend fun changeCurrentUser(uid: String)
    suspend fun signOut()
}