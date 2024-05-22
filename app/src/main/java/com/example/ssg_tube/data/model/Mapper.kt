package com.example.ssg_tube.data.model

import com.example.ssg_tube.presentaion.model.ChannelInfo
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.FormatManager

fun VideoItem.toVideoModel(): VideoModel {
    return VideoModel(
        videoId = this.id,
        thumbnail = this.snippet.thumbnails.high.url,
        title = this.snippet.title,
        date = this.snippet.publishedAt,
        description = this.snippet.description,
        channelId = this.snippet.channelId
    )
}

fun ChannelItem.toChannelInfo(): ChannelInfo {
    return ChannelInfo(
        id = this.id,
        thumbnail = this.snippet.thumbnails.high.url
    )
}

fun SearchItem.toVideoModel(): VideoModel {
    return VideoModel(
        videoId = this.id.videoId,
        title = this.snippet.title,
        thumbnail = this.snippet.thumbnails.high.url,
        date = FormatManager.dateFormat(this.snippet.date),
        description = this.snippet.description,
        channelId = this.snippet.channelId

    )
}