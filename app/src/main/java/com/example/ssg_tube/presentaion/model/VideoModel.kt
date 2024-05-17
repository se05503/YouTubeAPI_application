package com.example.ssg_tube.presentaion.model

import android.os.Parcelable
import com.example.ssg_tube.data.model.SearchVideoId
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoModel(
    val id : String,
    val thumbnail: String,
    val title: String,
    val date: String,
    val channelIcon: String,
    val channelName: String,
    val description: String,
    var liked: Boolean = false,
    val channelId : String
) : Parcelable




