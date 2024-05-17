package com.example.ssg_tube.data.repository

import com.example.ssg_tube.data.model.VideoResponse
import com.example.ssg_tube.data.remote.YouTubeAPI
import com.example.ssg_tube.presentaion.repository.SearchRepository

class SearchRepositoryImpl(private val youTubeAPI: YouTubeAPI) : SearchRepository {
    override suspend fun videoPopularList(
        part: String,
        chart: String,
        regionCode: String
    ): VideoResponse {
        return youTubeAPI.videoPopularList(part, chart, regionCode)
    }
}