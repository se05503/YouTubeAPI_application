package com.example.ssg_tube.data.model

import com.google.gson.annotations.SerializedName

// 채널
data class ChannelResponse(
    @SerializedName("items") val items: List<ChannelItem>
)

data class ChannelItem(
    @SerializedName("id") val id: String, // YouTube가 채널을 고유하게 식별하는 데 사용하는 ID입니다.
    @SerializedName("snippet") val snippet: ChannelSnippet
)

data class ChannelSnippet(
    @SerializedName("title") val title: String, // 채널의 제목입니다.
    @SerializedName("description") val description: String, // 채널의 설명입니다.
    @SerializedName("thumbnails") val thumbnails: ChannelThumbnailKey
)

data class ChannelThumbnailKey(
    @SerializedName("default") val default: ChannelThumbnailValue,
    @SerializedName("medium") val medium: ChannelThumbnailValue,
    @SerializedName("high") val high: ChannelThumbnailValue
)

data class ChannelThumbnailValue(
    @SerializedName("url") val url: String
)

