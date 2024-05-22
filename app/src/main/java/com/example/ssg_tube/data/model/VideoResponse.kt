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
    @SerializedName("statistics") val statistics: VideoStatistics,
    @SerializedName("nextTokenPage") val nextTokenPage: String,
    @SerializedName("prevTokenPage") val prevTokenPage: String,
    @SerializedName("pageInfo") val pageInfo: VideoPageInfoItem
)

data class VideoSnippet(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("channelTitle") val channelTitle: String,
    @SerializedName("thumbnails") val thumbnails: VideoThumbnailQuality,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("categoryId") val categoryId: String,
    @SerializedName("channelId") val channelId: String
)

data class VideoThumbnailQuality(
    @SerializedName("default") val default: VideoThumbnail,
    @SerializedName("medium") val medium: VideoThumbnail,
    @SerializedName("high") val high: VideoThumbnail
)

data class VideoThumbnail(
    @SerializedName("url") val url: String
)

data class VideoStatistics(
    @SerializedName("viewCount") val viewCount: String,
    @SerializedName("likeCount") val likeCount: String
)

data class VideoPageInfoItem(
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("resultsPerPage") val resultsPerPage: Int
)

