package com.pnam.watchingsocceronline.presentationphone.utils

import com.pnam.watchingsocceronline.model.model.*
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.random.Random

@FlowPreview
@ExperimentalCoroutinesApi
object FakeData {

    private fun <T> CancellableContinuation<T>.resume(t: T) {
        resume(t) {}
    }

    suspend fun getFakeUser(): User = suspendCancellableCoroutine {
        it.resume(user)
    }

    private val user: User by lazy {
        User(
            1323.toString(),
            "https://scontent-hkg4-2.xx.fbcdn.net/v/t1.6435-9/117337543_1232748220451392_486736325028794565_n.jpg?_nc_cat=109&ccb=1-3&_nc_sid=09cbfe&_nc_ohc=bywnYaEFxvcAX82QrFi&_nc_ht=scontent-hkg4-2.xx&oh=889feaf4525a0c09e6d416ed05a81359&oe=60D73032",
            "phuongnam@gmail.com",
            "phuongnam123",
            "Phạm",
            "Phương Nam",
            916678800000,
            User.Gender.MALE
        )
    }

    suspend fun getFakeVideos(): MutableList<Video> = suspendCancellableCoroutine {
        it.resume(videos)
    }

    private val videos: MutableList<Video> by lazy {
        mutableListOf(
            Video(
                Random(Calendar.getInstance().timeInMillis).nextLong().toString(),
                "Barsa vs Alético",
                "https://scontent-sin6-3.xx.fbcdn.net/v/t1.15752-9/106685602_744950729587468_192808375200785458_n.png?_nc_cat=104&ccb=1-3&_nc_sid=ae9488&_nc_ohc=exA_2GFYpNsAX9u-mip&_nc_ht=scontent-sin6-3.xx&oh=ea274389e21d3f32e98ca55f194e9318&oe=60CCE96B",
                "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
                700,
                1618219260000,
                "N/A",
                "N/A",
                "N/A",
                mutableListOf(
                    Comment(
                        2342,
                        "Trận đấu hay quá",
                        1618219270000,
                        user
                    )
                ),
                Match(
                    22,
                    Team(
                        121,
                        "Barsa",
                        2
                    ),
                    Team(
                        232,
                        "Alético",
                        3
                    )
                )
            ),
        )
    }

    fun getFakeSearchHistory(searchWord: String? = null): Flow<MutableList<SearchHistory>> =
        MutableStateFlow<MutableList<SearchHistory>>(mutableListOf()).apply {
            value = search
        }.debounce(200).flatMapLatest { history ->
            flow {
                if (searchWord == null) {
                    emit(history)
                } else {
                    val searchHistory =
                        history.filter { it.searchWord.contains(searchWord, true) }.toSet()
                            .toMutableList()
                    val videos =
                        getFakeVideos().filter { it.title.contains(searchWord, true) }.toSet()
                            .toMutableList()
                    searchHistory.addAll(videos.map {
                        SearchHistory(
                            searchWord = it.title,
                            searchTime = System.currentTimeMillis(),
                            searchType = SearchHistory.SearchType.SUGGESTION
                        )
                    })
                    emit(searchHistory)
                }
            }
        }

    private val search: MutableList<SearchHistory> by lazy {
        mutableListOf(
            SearchHistory(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Barca",
                1620018660000,
                SearchHistory.SearchType.HISTORY
            ),
            SearchHistory(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                "Real",
                1620018660000,
                SearchHistory.SearchType.HISTORY
            ),
        )
    }

    suspend fun getFakeNotification(): MutableList<Notification> = suspendCancellableCoroutine {
        it.resume(notification)
    }

    private val notification: MutableList<Notification> by lazy {
        mutableListOf(
            Notification(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                videos[4].vid,
                videos[4].title,
                videos[4].thumbnail,
                videos[4].showTime
            ),
            Notification(
                Random(Calendar.getInstance().timeInMillis).nextLong(),
                videos[0].vid,
                videos[0].title,
                videos[0].thumbnail,
                videos[0].showTime
            )
        )
    }
}