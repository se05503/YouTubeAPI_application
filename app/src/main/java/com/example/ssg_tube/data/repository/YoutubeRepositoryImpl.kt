package com.example.ssg_tube.data.repository

import com.example.ssg_tube.data.remote.YouTubeAPI
import com.example.ssg_tube.presentaion.model.ChannelInfo
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.repository.YoutubeRepository

class YoutubeRepositoryImpl(private val api: YouTubeAPI) : YoutubeRepository {
    override suspend fun getPopularVideos(): List<VideoModel> {
        val response = api.videoPopularList(
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
        val response = api.videoCategoriesList(
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
        val response = api.videoChannel(
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
}