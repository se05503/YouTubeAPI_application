package com.example.ssg_tube.presentaion.mypage

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ssg_tube.data.db.DBManager
import com.example.ssg_tube.presentaion.model.VideoModel

class MyPageViewModel: ViewModel() {

    // 하트 표시된 아이템들에 대한 LiveData
    private val _likedItems = MutableLiveData<List<VideoModel>>()
    val likedItems: LiveData<List<VideoModel>> get() = _likedItems

    // 뷰모델에서 라이브 데이터를 설정합니다.
    fun getHeartItems(context: Context) {
        _likedItems.value = DBManager.getPrefHeartItems(context) // loadData 를 써보려다가 데이터를 한번에 가져오기가 조금 힘들 것 같아 다른 함수를 사용했습니다!
    }
}