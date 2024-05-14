package com.example.ssg_tube.presentaion.model

data class DetailModel(
    val thumbnail: String,
    val title: String,
    val date: String,
    val channelIcon: String,
    val channelName: String,
    val description: String,
    val liked: Boolean = false
)
