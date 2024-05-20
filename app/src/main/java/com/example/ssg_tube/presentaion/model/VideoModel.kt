package com.example.ssg_tube.presentaion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoModel(
    val videoId : String, // Id 라고 쓰면 channelId 랑 의미 구분이 불분명해서 videoId로 바꾸었습니다!
    val thumbnail: String,
    val title: String,
    val date: String,
    val channelIcon: String,
    val channelName: String,
    val description: String,
    var liked: Boolean = false,
    val channelId : String
) : Parcelable




