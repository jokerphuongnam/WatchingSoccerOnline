package com.pnam.watchingsocceronline.data.utils

import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.domain.model.SearchHistory
import com.pnam.watchingsocceronline.domain.model.User
import com.pnam.watchingsocceronline.domain.model.Video
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.*
import kotlin.random.Random

private fun <T> CancellableContinuation<T>.resume(t: T) {
    resume(t) {}
}

suspend fun getFakeUsers(): List<User> = suspendCancellableCoroutine {
    it.resume(users)
}

fun addUser(user: User): User {
    user.uid = Random(System.currentTimeMillis()).nextLong().toString()
    users.add(user)
    return user
}

private val users: MutableList<User> by lazy {
    mutableListOf(
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
    )
}

suspend fun getFakeVideos(): MutableList<Video> = suspendCancellableCoroutine {
    it.resume(videos)
}

private val videos: MutableList<Video> by lazy {
    mutableListOf(
        Video(
            3424.toString(),
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
                    2342.toString(),
                    "Trận đấu hay quá",
                    1618219270000,
                    users[0]
                )
            )
        ),
        Video(
            5231.toString(),
            "Gym test video",
            "https://image.freepik.com/free-psd/intense-fitness-exercise-youtube-channel-thumbnail-web-banner_124868-205.jpg",
            "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
            500,
            1618219260000,
            "N/A",
            "N/A",
            "N/A",
            mutableListOf(
                Comment(
                    2342.toString(),
                    "Trận đấu hay quá",
                    1618219270000,
                    users[0]
                )
            )
        ),
        Video(
            33152.toString(),
            "Notification test video",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Football_iu_1996.jpg/1200px-Football_iu_1996.jpg",
            "",
            100,
            1628614800000,
            "N/A",
            "N/A",
            "N/A",
            mutableListOf()
        ),
        Video(
            75675.toString(),
            "Notification test video",
            "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Football_iu_1996.jpg/1200px-Football_iu_1996.jpg",
            "https://soccerbats.b-cdn.net/BARCA%20vs%20REAL%20MADRID.mp4",
            100,
            1623218400000,
            "N/A",
            "N/A",
            "N/A",
            mutableListOf()
        )
    )
}

fun writeFakeComment(content: String, vid: String, uid: String? = null): Boolean {
    for (fakeVideo in videos) {
        if (fakeVideo.vid == vid) {
            val comments = fakeVideo.comments.toMutableList()
            for (user in users) {
                if (user.uid == uid) {
                    comments.add(
                        Comment(
                            System.currentTimeMillis().toString(),
                            content,
                            System.currentTimeMillis(),
                            user
                        )
                    )
                    fakeVideo.comments = comments
                }
            }
            return true
        }
    }
    return false
}

suspend fun getFakeSearchHistory(searchWord: String? = null): MutableList<SearchHistory> =
    suspendCancellableCoroutine {
        if (searchWord == null || searchWord.isEmpty()) {
            it.resume(search)
        } else {
            it.resume(search.filter { search -> search.searchWord.contains(searchWord, true) }
                .toMutableList())
        }
    }

suspend fun getFakeFilterVideo(searchWord: String? = null): MutableList<Video> =
    suspendCancellableCoroutine {
        if (searchWord == null || searchWord.isEmpty()) {
            it.resume(mutableListOf())
        } else {
            it.resume(videos.filter { video -> video.title.contains(searchWord, true) }
                .toMutableList())
        }
    }

private val search: MutableList<SearchHistory> by lazy {
    mutableListOf(
        SearchHistory(
            Random(Calendar.getInstance().timeInMillis).nextLong().toString(),
            "Barca",
            1620018660000,
            SearchHistory.SearchType.HISTORY
        ),
        SearchHistory(
            Random(Calendar.getInstance().timeInMillis).nextLong().toString(),
            "Real",
            1620018660000,
            SearchHistory.SearchType.HISTORY
        ),
    )
}