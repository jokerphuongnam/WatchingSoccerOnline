package com.pnam.watchingsocceronline.data.database.local.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.pnam.watchingsocceronline.data.database.local.CurrentUser
import com.pnam.watchingsocceronline.data.utils.CURRENT_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreCurrentUserImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : CurrentUser {
    override val uid: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[CURRENT_ID]
        }

    override suspend fun changeCurrentUser(uid: String) {
        dataStore.edit { setting ->
            setting[CURRENT_ID] = uid
        }
    }

    override suspend fun signOut() {
        dataStore.edit { setting ->
            setting[CURRENT_ID] = "N/A"
        }
    }
}