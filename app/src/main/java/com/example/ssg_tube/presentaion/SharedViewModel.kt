package com.example.ssg_tube.presentaion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private val _unlikedItemsUrl = MutableLiveData<List<String>>()
    val unlikedItemsUrl: LiveData<List<String>> get() = _unlikedItemsUrl

    private var currentList = ArrayList<String>()

    fun addUnlikedItemUrl(id:String) {
        currentList.add(id)
        _unlikedItemsUrl.value = currentList
    }

    fun delete

    fun clearItemUrl() {
        _unlikedItemsUrl.value = emptyList()
    }
}