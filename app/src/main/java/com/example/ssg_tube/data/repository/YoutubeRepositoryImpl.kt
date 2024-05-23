package com.example.ssg_tube.data.repository

import com.example.ssg_tube.data.model.toChannelInfo
import com.example.ssg_tube.data.model.toVideoModel
import com.example.ssg_tube.data.remote.YouTubeAPI
import com.example.ssg_tube.presentaion.model.ChannelInfo
import com.example.ssg_tube.presentaion.model.SearchToken
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.repository.YoutubeRepository

class YoutubeRepositoryImpl(private val apiService: YouTubeAPI) : YoutubeRepository {
    override suspend fun getPopularVideos(pageToken: String): List<VideoModel> {
        val response = apiService.videoPopularList(
            part = "snippet",
            chart = "mostPopular",
            regionCode = "KR",
            pageToken = pageToken
        )
        return response.items.map { it.toVideoModel() }
    }

    override suspend fun getCategoryVideos(
        categoryId: String,
        pageToken: String
    ): List<VideoModel> {
        val response = apiService.videoCategoriesList(
            part = "snippet",
            chart = "mostPopular",
            regionCode = "KR",
            videoCategoryId = categoryId,
            pageToken = pageToken
        )
        return response.items.map { it.toVideoModel() }
    }

    override suspend fun getChannel(channelId: List<String>, pageToken: String): List<ChannelInfo> {
        val response = apiService.videoChannel(
            part = "snippet",
            id = channelId.joinToString(","),
            pageToken = pageToken
        )
        return response.items.map { it.toChannelInfo() }
    }

    override suspend fun getSearch(
        query: String,
        order: String,
        pageToken: String
    ): SearchToken {
        val response = apiService.videoSearch(
            part = "snippet",
            query = query,
            maxResults = 20,
            order = order,
            type = "video",
            videoType = "any",
            pageToken = pageToken
        )

        val nextPageToken = response.nextPageToken // 무한 스크롤 뷰를 위해 nextPageToken 값을 받아옵니다.
        val videoModels = response.items.map { it.toVideoModel() }
        return SearchToken(items = videoModels, nextToken = nextPageToken)
    }
}