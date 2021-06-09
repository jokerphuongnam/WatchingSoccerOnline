package com.pnam.watchingsocceronline.data.database.network.impl

import com.pnam.watchingsocceronline.data.database.network.SearchNetwork
import com.pnam.watchingsocceronline.data.utils.getFakeSearchHistory
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import javax.inject.Inject

class FakeSearchNetworkImpl @Inject constructor() : SearchNetwork {
    override suspend fun fetchSearchHistory(
        uid: String,
        searchWord: String?
    ): MutableList<SearchHistory> {
        return getFakeSearchHistory(searchWord)
    }
}