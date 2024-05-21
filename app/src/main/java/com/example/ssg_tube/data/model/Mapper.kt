package com.example.ssg_tube.data.model

import com.example.ssg_tube.presentaion.model.ChannelInfo
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.FormatManager

// videoItem.videoMapper
// 함수 이름 toVideoMapper
// 확장함수를 쓰면 this로 접근 가능
// 편하게 각 레이어별로 mapper가 있다고 생각해라.

fun VideoItem.toDomainVideoModel(): VideoModel {
    return VideoModel( // videoInfo
        videoId = this.id,
        thumbnail = this.snippet.thumbnails.default.url,
        title = this.snippet.title,
        date = this.snippet.publishedAt,
        description = this.snippet.description,
        channelIcon = "",
        channelName = "",
        channelId = this.snippet.channelId
    )
}

fun ChannelItem.toDomainChannelModel(): ChannelInfo {
    return ChannelInfo(
        id = this.id,
        thumbnail = this.snippet.thumbnails.high.url
    )
}

fun SearchItem.toDomainSearchModel(): VideoModel {
    return VideoModel( // searchInfo
        videoId = this.id.videoId,
        title = this.snippet.title,
        thumbnail = this.snippet.thumbnails.high.url,
        channelIcon = "",
        channelName = "",
        date = FormatManager.dateFormat(this.snippet.date),
        description = this.snippet.description,
        channelId = this.snippet.channelId
    )
}