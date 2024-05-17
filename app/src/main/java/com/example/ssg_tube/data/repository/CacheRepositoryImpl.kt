package com.example.ssg_tube.data.repository

import android.content.Context
import com.example.ssg_tube.data.db.DBManager
import com.example.ssg_tube.presentaion.repository.CacheRepository

class CacheRepositoryImpl(private val dbManager : DBManager) : CacheRepository {
    override fun <T> saveData(context: Context, key: String, obj: T) {
        return dbManager.saveData(context, key, obj)
    }
}