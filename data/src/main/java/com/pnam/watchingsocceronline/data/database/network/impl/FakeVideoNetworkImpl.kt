package com.pnam.watchingsocceronline.data.database.network.impl

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.utils.*
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.domain.model.Video
import javax.inject.Inject

class FakeVideoNetworkImpl @Inject constructor() : VideoNetwork {
    override suspend fun fetchVideos(): MutableList<Video> {
        return getFakeVideos()
    }

    @Throws(Resources.NotFoundException::class)
    override suspend fun fetchVideo(vid: Long, uid: Long?): Video {
        val fakeVideos = getFakeVideos()
        for (fakeVideo in fakeVideos) {
            if (fakeVideo.vid == vid) {
                return fakeVideo
            }
        }
        throw Resources.NotFoundException()
    }

    @Throws(Resources.NotFoundException::class)
    override suspend fun fetchComments(vid: Long): List<Comment> {
        val fakeVideos = getFakeVideos()
        for (fakeVideo in fakeVideos) {
            if (fakeVideo.vid == vid) {
                return fakeVideo.comments
            }
        }
        throw Resources.NotFoundException()
    }

    override suspend fun writeComment(content: String, vid: Long, uid: Long?) {
        writeFakeComment(content, vid, uid).takeUnless { it }?.let {
            throw Resources.NotFoundException()
        }
    }

    override suspend fun fetchChart(filter: Filter): List<Video> {
        return getFakeVideos()
    }

    override suspend fun fetchFilterVideo(searchWord: String?): List<Video> {
        return getFakeFilterVideo(searchWord)
    }
}