package com.example.ssg_tube.presentaion.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.ssg_tube.data.db.DBManager
import com.example.ssg_tube.data.repository.CacheRepositoryImpl
import com.example.ssg_tube.data.repository.SearchRepositoryImpl
import com.example.ssg_tube.network.RetroClient
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.repository.CacheRepository
import com.example.ssg_tube.presentaion.repository.SearchRepository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val searchRepository: SearchRepository,
    private val cacheRepository: CacheRepository
) : ViewModel() {
    private val _event: MutableLiveData<List<VideoModel>> = MutableLiveData()
    val event: LiveData<List<VideoModel>> get() = _event

    fun getVideoPopularList(
        part: String,
        chart: String,
        regionCode: String
    ) {
        viewModelScope.launch {
            searchRepository.videoPopularList(part, chart, regionCode)
        }
    }

    fun <T> saveData(context: Context, key: String, obj: T) {
        cacheRepository.saveData(context, key, obj)
    }
}

class HomeViewModelFactory : ViewModelProvider.Factory {
    private val searchRepository = SearchRepositoryImpl(RetroClient.youTubeRetrofit)
    private val cacheRepository = CacheRepositoryImpl(DBManager)
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras
    ): T {
        return HomeViewModel(
            searchRepository,
            cacheRepository
        ) as T
    }
}