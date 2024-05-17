package com.example.ssg_tube.presentaion.repository

import android.content.Context

interface CacheRepository {
    fun <T> saveData(context: Context, key: String, obj: T)
}