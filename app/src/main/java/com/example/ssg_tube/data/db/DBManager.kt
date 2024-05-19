package com.example.ssg_tube.data.db

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.example.ssg_tube.Constants
import com.example.ssg_tube.presentaion.model.VideoModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder

object DBManager {
    private val gson = Gson()

    private fun getPreferences(context: Context, name: String): SharedPreferences {
        return context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    // requireContext, Constants key,
    // context는 보통 requireContext 일것이다. key는 말그대로 키캆
    // obj는 객체 Model dataclass 이라던지 그니까 뭐든 상관없음 어차피 json으로 변환 시킬것이니까.
    fun <T> saveData(context: Context, key: String, obj: T) {
        val json = gson.toJson(obj) // 객체를 Json문자열로 변환후 저장
        //Log.d("SaveData", "저장된 값 $json")
        getPreferences(context, Constants.PREF).edit().apply {
            putString(key, json)
            apply()
        }
    }

    // 저장된 json문자열을 객체로 다시 변환해서 로드함 ( 반환값은 객체 )
    // 로드할 데이터 타입을 Gson에게 알려줘야함 떄문에 해당 클래스의 정보가 필요함
    // shared preference 에서 한개의 값을 꺼내오는 경우
    fun <T> loadData(context: Context, key: String, clazz: Class<T>): T? {
        val json = getPreferences(context, Constants.PREF).getString(key, null)
        //Log.d("LoadData", "저장된 값 $json")
        return json?.let { gson.fromJson(it, clazz) }
    }

    // shared prefence 에 저장된 아이템들을 모두 꺼내오는 함수입니다.
    // loadData 함수와 b type 해설강의의 함수를 같이 반영해서 만들었습니다.
    fun getPrefHeartItems(context: Context): List<VideoModel> {
        val prefs = getPreferences(context,Constants.PREF)
        val allHeartItems: Map<String, *> = prefs.all
        val heartItems = ArrayList<VideoModel>()
        for ((key, value) in allHeartItems) {
            val item = gson.fromJson(value as String, VideoModel::class.java)
            heartItems.add(item)
        }
        return heartItems.toList()
    }

    fun removeData(context: Context, key: String) {
        getPreferences(context, Constants.PREF).edit().apply {
            remove(key)
            apply()
        }
    }
}