package com.example.ssg_tube.presentaion.util

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.ssg_tube.R
import com.example.ssg_tube.presentaion.model.VideoModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.gson.GsonBuilder

fun FragmentActivity.invisible() {
    val layout = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    layout.visibility = View.GONE
}

fun FragmentActivity.visible(){
    val layout = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    layout.visibility = View.VISIBLE
}