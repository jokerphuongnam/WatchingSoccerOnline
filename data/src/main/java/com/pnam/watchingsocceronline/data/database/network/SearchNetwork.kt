package com.pnam.watchingsocceronline.data.database.network

import com.pnam.watchingsocceronline.domain.model.SearchHistory
import javax.inject.Singleton

@Singleton
interface SearchNetwork {
   suspend fun fetchSearchHistory(searchWord: String? = null): MutableList<SearchHistory>
}