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
    @SerializedName("thumbnails") val thumbnails: ThumbnailChannelDefault
)

data class ThumbnailChannelDefault(
    @SerializedName("default") val default: ThumbnailChannelUrl
)

data class ThumbnailChannelUrl(
    @SerializedName("url") val url: String?
)
