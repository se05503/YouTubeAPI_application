package com.example.ssg_tube.presentaion.repository

import com.example.ssg_tube.presentaion.model.ChannelInfo
import com.example.ssg_tube.presentaion.model.SearchToken
import com.example.ssg_tube.presentaion.model.VideoModel

interface YoutubeRepository {
    suspend fun getPopularVideos(pageToken: String): List<VideoModel>
    suspend fun getCategoryVideos(categoryId: String, pageToken: String): List<VideoModel>
    suspend fun getChannel(channelId: List<String>, pageToken: String): List<ChannelInfo>
    suspend fun getSearch(query: String, order: String, pageToken: String): SearchToken
}