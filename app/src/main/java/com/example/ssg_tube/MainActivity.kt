package com.example.ssg_tube

import android.os.Bundle
import android.util.Log
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
    private val searchFragment = SearchFragment() // 매번 새로운 프래그먼트를 생성하는게 아니라 변수를 미리 생성하고 그것을 replace 해야함

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity","onCreate")
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.clMain)) { v, insets ->
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
                        .replace(R.id.flMain, HomeFragment()).commit()
                    true
                }

                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .addToBackStack(null) // 프래그먼트로 다시 돌아왔을 때 onAttach 가 아닌 onCreateView 부터 시작할 수 있도록 해서 뷰가 유지되도록 해줌
                        .setReorderingAllowed(true)
                        .replace(R.id.flMain, searchFragment).commit()
                    true
                }

                R.id.myPageFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.flMain, MyPageFragment()).commit()
                    true
                }

                else -> false
            }
        }
    }
}