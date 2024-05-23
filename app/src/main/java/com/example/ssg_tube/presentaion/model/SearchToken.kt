package com.example.ssg_tube.presentaion.model

data class SearchToken(
    val items: List<VideoModel>,
    val nextToken: String?
)
