package com.example.ssg_tube.data.model

import com.google.gson.annotations.SerializedName

// 참고 url: https://developers.google.com/youtube/v3/docs/search?hl=ko
// 검색
data class SearchResponse(
    @SerializedName("items") val items: List<SearchItem>
)

data class SearchItem(
    @SerializedName("id") val id: SearchVideoId,
    @SerializedName("snippet") val snippet: VideoSearchSnippet
)

data class SearchVideoId(
    @SerializedName("kind") val kind: String,
    @SerializedName("videoId") val videoId: String,

)

data class VideoSearchSnippet(
    @SerializedName("title") val title:String, // 검색 결과의 제목입니다.
    @SerializedName("thumbnails") val thumbnails: Map<String,Thumbnail>,
    @SerializedName("channelId") val channelId : String
)

data class Thumbnail(
    @SerializedName("url") val url: String
)
