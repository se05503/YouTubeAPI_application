package com.example.ssg_tube.presentaion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailModel(
    val thumbnail: String,
    val title: String,
    val date: String,
    val channelIcon: String,
    val channelName: String,
    val description: String,
    val liked: Boolean = false
):Parcelable


//1. 홈모델에서 데이터를 받아올때, 필요한 정보만 받을 것이냐 -> ok -> 그럼 나는 내가 필요한 건 어디서, 어떻게 받아오냐?
//2. 홈에서 값을 보낼거야 #디테일 -> 그럼 나는 왜 모델이 필요하냐? ->

// Flow ->








