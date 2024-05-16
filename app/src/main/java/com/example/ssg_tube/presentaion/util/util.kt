package com.example.ssg_tube.presentaion.util

import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.ssg_tube.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

fun FragmentActivity.invisible() {
    val layout = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    layout.visibility = View.GONE
}

fun FragmentActivity.visible(){
    val layout = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    layout.visibility = View.VISIBLE
}