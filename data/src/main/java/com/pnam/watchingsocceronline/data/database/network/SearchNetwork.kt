package com.pnam.watchingsocceronline.data.database.network

import com.pnam.watchingsocceronline.domain.model.SearchHistory
import javax.inject.Singleton

@Singleton
interface SearchNetwork {
   suspend fun fetchSearchHistory(uid: String, searchWord: String? = null): List<SearchHistory>
}