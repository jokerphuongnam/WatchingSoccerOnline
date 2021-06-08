package com.pnam.watchingsocceronline.data.database.local.impl

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.utils.CURRENT_ID
import com.pnam.watchingsocceronline.data.utils.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreCurrentUserImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CurrentUser {

    override suspend fun findUid(): String? {
        return context.dataStore.data.map { preferences ->
            preferences[CURRENT_ID]
        }.first().takeUnless { it == null || it.isEmpty() }
    }

    override suspend fun changeCurrentUser(uid: String) {
        context.dataStore.edit { setting ->
            setting[CURRENT_ID] = uid
        }
    }

    override suspend fun signOut() {
        context.dataStore.edit { setting ->
            setting[CURRENT_ID] = ""
        }
    }
}