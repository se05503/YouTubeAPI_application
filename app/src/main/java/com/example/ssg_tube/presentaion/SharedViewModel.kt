package com.example.ssg_tube.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private val _unlikedItemsUrl = MutableLiveData<List<String>>()
    val unlikedItemsUrl: LiveData<List<String>> get() = _unlikedItemsUrl

    // 좋아요를 취소해야할 아이템에 대한 videoId 를 담습니다.
    private var currentList = ArrayList<String>()

    // 지워야할 아이템에 대한 아이디 값을 담는 함수입니다.
    fun addItemId(id:String) {
        currentList.add(id)
    }

    fun deleteItemId(id:String) {
        currentList.remove(id)
    }

    // Detail Fragment 를 종료할 때 호출 → 이때만 옵져브 하는 방식
    fun notifyLiveDataChanged() {
        _unlikedItemsUrl.value = currentList
    }

    fun clearItemUrl() {
        currentList.clear()
    }
}