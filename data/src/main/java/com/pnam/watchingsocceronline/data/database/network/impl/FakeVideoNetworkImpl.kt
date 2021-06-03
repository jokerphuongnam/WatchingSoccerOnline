package com.pnam.watchingsocceronline.data.database.network.impl

import android.content.res.Resources
import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.data.utils.getFakeFilterVideo
import com.pnam.watchingsocceronline.data.utils.getFakeVideos
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
    override suspend fun fetchComment(vid: Long): MutableList<Comment> {
        val fakeVideos = getFakeVideos()
        for (fakeVideo in fakeVideos) {
            if (fakeVideo.vid == vid) {
                return fakeVideo.comments
            }
        }
        throw Resources.NotFoundException()
    }

    override suspend fun fetchChart(filter: Filter): MutableList<Video> {
        return getFakeVideos()
    }

    override suspend fun fetchFilterVideo(searchWord: String?): MutableList<Video> {
        return getFakeFilterVideo(searchWord)
    }
}