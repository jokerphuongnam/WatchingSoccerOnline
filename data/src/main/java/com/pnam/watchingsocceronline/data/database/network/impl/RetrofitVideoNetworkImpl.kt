package com.pnam.watchingsocceronline.data.database.network.impl

import com.pnam.watchingsocceronline.data.database.network.VideoNetwork
import com.pnam.watchingsocceronline.data.database.network.dto.VideoResponse
import com.pnam.watchingsocceronline.data.utils.Filter
import com.pnam.watchingsocceronline.data.utils.RetrofitUtils.NOT_FOUND
import com.pnam.watchingsocceronline.data.utils.toReactVideo
import com.pnam.watchingsocceronline.domain.model.Comment
import com.pnam.watchingsocceronline.domain.model.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject

class RetrofitVideoNetworkImpl @Inject constructor(
    private val service: VideoService
) : VideoNetwork {

    override suspend fun fetchVideos(): List<Video> {
        return service.fetchVideos().body()!!.map { it.toVideo() }
    }

    override suspend fun fetchVideo(vid: String, uid: String?): Video {
        return service.fetchVideo(vid).body()!!.toVideo().apply {
            uid ?: return@apply
            reactVideo = service.fetchReactVideo(vid, uid).body()!!.toReactVideo()
        }
    }

    override suspend fun fetchRecommendVideos(): List<Video> {
        return service.fetchRecommendVideos().body()!!.map { it.toVideo() }
    }

    override suspend fun fetchComments(vid: String): List<Comment> {
        TODO()
    }

    override suspend fun writeComment(content: String, vid: String, uid: String?) {
        TODO()
    }

    override suspend fun fetchChart(filter: Filter): List<Video> {
        TODO()
    }

    override suspend fun fetchFilterVideo(searchWord: String?): List<Video> {
        return emptyList()
    }

    override suspend fun fetchSearchResultVideos(uid: String, searchWord: String): List<Video> {
        val response = service.fetchResultVideos(uid, searchWord)
        return if (response.code().equals(NOT_FOUND)) {
            mutableListOf()
        } else {
            response.body()!!.map { it.toVideo() }
        }
    }

    interface VideoService {

        @GET("api/video/getall")
        suspend fun fetchVideos(): Response<List<VideoResponse>>

        @GET("api/video/{id}/watch")
        suspend fun fetchVideo(
            @Path("id") vid: String
        ): Response<VideoResponse>

        @GET("api/video/react/{uid}/{vid}")
        suspend fun fetchReactVideo(
            @Path("vid") vid: String,
            @Path("uid") uid: String
        ): Response<String>

        @GET("/api/video/random/5")
        suspend fun fetchRecommendVideos(): Response<List<VideoResponse>>

        //        suspend fun fetchComments(vid: String): List<Comment>
//
//        suspend fun writeComment(content: String, vid: String, uid: String?)
//
//        suspend fun fetchChart(filter: Filter): List<Video>
//
//        suspend fun fetchFilterVideo(searchWord: String?): List<Video>
//
        @POST("api/user/{uid}/search/{search}")
        suspend fun fetchResultVideos(
            @Path("uid") uid: String,
            @Path("search") searchWord: String?
        ): Response<List<VideoResponse>>
    }
}