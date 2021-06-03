package com.pnam.watchingsocceronline.data.repository

import com.pnam.watchingsocceronline.data.database.network.SearchNetwork
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import javax.inject.Singleton

@Singleton
interface SearchRepository {
    val searchNetwork: SearchNetwork

    suspend fun getSearchHistory(searchWord: String? = null): MutableList<SearchHistory>
}