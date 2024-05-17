package com.example.ssg_tube.presentaion.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChannelModel(
    val id: String,
    val title: String,
    val thumbnail: String
) : Parcelable
