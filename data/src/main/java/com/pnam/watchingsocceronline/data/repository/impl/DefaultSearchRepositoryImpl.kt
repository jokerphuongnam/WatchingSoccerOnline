package com.pnam.watchingsocceronline.data.repository.impl

import com.pnam.watchingsocceronline.data.database.network.SearchNetwork
import com.pnam.watchingsocceronline.data.repository.SearchRepository
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import javax.inject.Inject

class DefaultSearchRepositoryImpl @Inject constructor(
    override val searchNetwork: SearchNetwork
) : SearchRepository {

    override suspend fun getSearchHistory(
        uid: String,
        searchWord: String?
    ): List<SearchHistory> {
        return searchNetwork.fetchSearchHistory(uid, searchWord)
    }
}