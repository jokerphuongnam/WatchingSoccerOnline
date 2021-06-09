package com.pnam.watchingsocceronline.data.database.network.impl

import com.pnam.watchingsocceronline.data.database.network.SearchNetwork
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

class RetrofitSearchNetworkImpl @Inject constructor(
    private val service: SearchService
) : SearchNetwork {
    override suspend fun fetchSearchHistory(
        uid: String,
        searchWord: String?
    ): List<SearchHistory> {
        return service.fetchSearchHistory(uid).body()!!.filter {
            if (searchWord == null || searchWord.isEmpty()) {
                true
            } else {
                it.contains(searchWord)
            }
        }.map {
            SearchHistory(
                "",
                it,
                1623121200000,
                SearchHistory.SearchType.HISTORY
            )
        }
    }

    interface SearchService {

        @GET("api/user/{id}/history")
        suspend fun fetchSearchHistory(
            @Path("id") uid: String
        ): Response<List<String>>
    }
}