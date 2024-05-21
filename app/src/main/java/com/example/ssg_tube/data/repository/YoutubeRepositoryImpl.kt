package com.example.ssg_tube.data.repository

import com.example.ssg_tube.data.remote.YouTubeAPI
import com.example.ssg_tube.presentaion.model.ChannelInfo
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.repository.YoutubeRepository
import com.example.ssg_tube.presentaion.util.FormatManager

class YoutubeRepositoryImpl(private val apiService: YouTubeAPI) : YoutubeRepository {
    override suspend fun getPopularVideos(): List<VideoModel> {
        val response = apiService.videoPopularList(
            part = "snippet",
            chart = "mostPopular",
            regionCode = "KR"
        )
        return response.items.map { item ->
            VideoModel(
                thumbnail = item.snippet.thumbnails.default.url,
                title = item.snippet.title,
                date = item.snippet.publishedAt,
                channelIcon = "",
                channelName = "",
                description = item.snippet.description,
                channelId = item.snippet.channelId,
                videoId = item.id
            )
        }
    }

    override suspend fun getCategoryVideos(categoryId: String): List<VideoModel> {
        val response = apiService.videoCategoriesList(
            part = "snippet",
            chart = "mostPopular",
            regionCode = "KR",
            videoCategoryId = categoryId
        )
        return response.items.map { item ->
            VideoModel(
                thumbnail = item.snippet.thumbnails.default.url,
                title = item.snippet.title,
                date = item.snippet.publishedAt,
                channelIcon = "",
                channelName = "",
                description = item.snippet.description,
                channelId = item.snippet.channelId,
                videoId = item.id
            )
        }
    }

    override suspend fun getChannel(channelId: List<String>): List<ChannelInfo> {
        val response = apiService.videoChannel(
            part = "snippet",
            id = channelId.joinToString(",")
        )
        return response.items.map { item ->
            ChannelInfo(
                id = item.id,
                thumbnail = item.snippet.thumbnails.default.url
            )
        }
    }

    override suspend fun getSearch(query: String, order: String): List<VideoModel> {
        val response = apiService.videoSearch(
            part = "snippet",
            query = query,
            maxResults = 20,
            order = order,
            type = "video",
            videoType = "any"
        )
        return response.items.map {item ->
            VideoModel(
                videoId = item.id.videoId,
                title = item.snippet.title,
                thumbnail = item.snippet.thumbnails.high.url,
                channelIcon = "",
                channelName = "",
                date = FormatManager.dateFormat(item.snippet.date),
                description = item.snippet.description,
                channelId = item.snippet.channelId
            )
        }
    }
}