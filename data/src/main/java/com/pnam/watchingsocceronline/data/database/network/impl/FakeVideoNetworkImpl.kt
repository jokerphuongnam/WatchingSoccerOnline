package com.pnam.watchingsocceronline.data.database.network.impl

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.data.utils.getFakeFilterVideo
import com.pnam.watchingsocceronline.data.utils.getFakeVideos
import com.pnam.watchingsocceronline.data.utils.writeFakeComment
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Inject

class FakeVideoNetworkImpl @Inject constructor() : VideoNetwork {
    override suspend fun fetchVideos(): List<Video> {
        return getFakeVideos()
    }

    @Throws(Resources.NotFoundException::class)
    override suspend fun fetchVideo(vid: String, uid: String?): Video {
        val fakeVideos = getFakeVideos()
        for (fakeVideo in fakeVideos) {
            if (fakeVideo.vid == vid) {
                return fakeVideo
            }
        }
        throw Resources.NotFoundException()
    }

    @Throws(Resources.NotFoundException::class)
    override suspend fun fetchComments(vid: String): List<Comment> {
        val fakeVideos = getFakeVideos()
        for (fakeVideo in fakeVideos) {
            if (fakeVideo.vid == vid) {
                return fakeVideo.comments
            }
        }
        throw Resources.NotFoundException()
    }

    override suspend fun writeComment(content: String, vid: String, uid: String): Comment {
        return writeFakeComment(content, vid, uid)
    }

    override suspend fun fetchChart(filter: Filter): List<Video> {
        return getFakeVideos()
    }

    override suspend fun fetchFilterVideo(searchWord: String): List<Video> {
        return getFakeFilterVideo(searchWord)
    }

    override suspend fun fetchSearchResultVideos(uid: String, searchWord: String): List<Video> {
        return getFakeFilterVideo(searchWord)
    }

    override suspend fun fetchRecommendVideos(): List<Video> {
        return getFakeVideos()
    }

    override suspend fun likeVideo(uid: String, vid: String):  Video {
        TODO("Not yet implemented")
    }

    override suspend fun dislikeVideo(uid: String, vid: String):  Video {
        TODO("Not yet implemented")
    }
}