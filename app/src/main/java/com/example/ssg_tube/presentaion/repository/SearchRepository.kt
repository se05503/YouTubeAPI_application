package com.example.ssg_tube.presentaion.repository

import com.example.ssg_tube.Constants
import com.example.ssg_tube.data.model.VideoResponse
import retrofit2.http.Query

interface SearchRepository {
    suspend fun videoPopularList(
        part: String,
        chart: String,
        regionCode: String
    ): VideoResponse
}