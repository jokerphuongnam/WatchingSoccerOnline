package com.pnam.watchingsocceronline.data.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val CURRENT_ID: Preferences.Key<Long> by lazy {
    longPreferencesKey("uid")
}

const val DATA_STORE_NAME = "settings_pref"

// At the top level of your kotlin file:
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
