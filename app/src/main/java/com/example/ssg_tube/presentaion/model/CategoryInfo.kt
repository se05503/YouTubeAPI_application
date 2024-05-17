package com.example.ssg_tube.presentaion.model

data class CategoryInfo (
    val id: String, // 카테고리 비디오의 고유 id 값을 식별하기위해 추가
    val title: String,
    val thumbnail: String,
    val channelId: String // Channel에 값을 줘야해서 channelId 값을 추가
)