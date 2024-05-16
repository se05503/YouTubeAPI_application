package com.example.ssg_tube

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ssg_tube.databinding.ActivityMainBinding
import com.example.ssg_tube.presentaion.home.HomeFragment
import com.example.ssg_tube.presentaion.mypage.MyPageFragment
import com.example.ssg_tube.presentaion.search.SearchFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        setupBottomNavigation()
        // 초기 클릭된 값이 없을 시 menu에서 homeFragment를 클릭 된 상태를 보여줌
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.homeFragment
        }


    }

    private fun setupBottomNavigation() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_main, HomeFragment()).commit()
                    true
                }

                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_main, SearchFragment()).commit()
                    true
                }

                R.id.myPageFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_main, MyPageFragment()).commit()
                    true
                }

                else -> false
            }
        }
    }
}