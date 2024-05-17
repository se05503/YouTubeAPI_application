package com.example.ssg_tube.data.model

import com.google.gson.annotations.SerializedName

// 채널
data class ChannelResponse(
    @SerializedName("items") val items: List<ChannelItem>
)

data class ChannelItem(
    @SerializedName("id") val id: String,
    @SerializedName("snippet") val snippet: ChannelSnippet
)

data class ChannelSnippet(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String,
    //확인해보니 동영상 디스크립션이 아닙니다
    @SerializedName("thumbnails") val thumbnails: ChannelThumbnails
)

data class ChannelThumbnail(
    @SerializedName("url") val url: String,
)
data class ChannelThumbnails(
    @SerializedName("high") val high: ChannelThumbnail,
    @SerializedName("medium") val medium: ChannelThumbnail,
    @SerializedName("default") val default: ChannelThumbnail
)
