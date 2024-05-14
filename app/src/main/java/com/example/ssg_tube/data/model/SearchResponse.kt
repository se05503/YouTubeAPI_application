package com.example.ssg_tube.data.model

import com.google.gson.annotations.SerializedName

// 검색
data class SearchResponse(
    @SerializedName("items") val items: List<SearchItem>
)

data class SearchItem(
    @SerializedName("id") val id: SearchVideoId,
    @SerializedName("snippet") val snippet: VideoSnippet
)

data class SearchVideoId(
    @SerializedName("kind") val kind: String,
    @SerializedName("videoId") val videoId: String
)

