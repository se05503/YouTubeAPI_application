package com.example.ssg_tube.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

// 비디오
data class VideoResponse(
    @SerializedName("items") val items: List<VideoItem>
)

data class VideoItem(
    @SerializedName("id") val id: String,
    @SerializedName("snippet") val snippet: VideoSnippet,
    @SerializedName("statistics") val statistics: VideoStatistics?
)

data class VideoSnippet(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("channelTitle") val channelTitle: String,
    @SerializedName("publishedAt") val publishedAt: Date,
    @SerializedName("thumbnails") val thumbnails: String = "default"
)

data class VideoStatistics(
    @SerializedName("viewCount") val viewCount: String,
    @SerializedName("likeCount") val likeCount: String?
)

