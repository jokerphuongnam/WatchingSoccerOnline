package com.pnam.watchingsocceronline.data.database.local.impl

import android.util.Log
import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.utils.getFakeUser
import com.pnam.watchingsocceronline.domain.model.User
import javax.inject.Inject

class DataStoreCurrentUserImpl @Inject constructor(
//    private val dataStore: DataStore<Preferences>
) : CurrentUser {

    override suspend fun findUid(): Long? {
        val user: User = getFakeUser() ?: return null
        return user.uid
//        return dataStore.data.map { preferences ->
//            preferences[CURRENT_ID]
//        }.first()
    }

    override suspend fun changeCurrentUser(uid: Long) {
//        dataStore.edit { setting ->
//            setting[CURRENT_ID] = uid
//        }
    }

    override suspend fun signOut() {
//        dataStore.edit { setting ->
//            setting[CURRENT_ID] = -1
//        }
    }
}