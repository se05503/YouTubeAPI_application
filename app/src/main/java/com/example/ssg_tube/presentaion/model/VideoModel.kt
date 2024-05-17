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
    val liked: Boolean = false,
    val channelId : String
) : Parcelable


//채널 아이디 값이 있을 수가 없음. 클릭한 아이템의 채널아이템을 가져와야함





