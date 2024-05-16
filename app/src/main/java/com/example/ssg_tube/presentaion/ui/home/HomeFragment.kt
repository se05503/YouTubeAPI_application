package com.example.ssg_tube.presentaion.ui.home

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.FragmentHomeBinding
import com.example.ssg_tube.presentaion.model.DetailModel

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var popularVideoAdapter: PopularVideoAdapter
    private lateinit var categoryVideoAdapter: CategoryVideoAdapter
    private lateinit var channelAdapter: ChannelAdapter
    private val viewModel: HomeViewModel by viewModels()

    val dummyData = mutableListOf<DetailModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

        dummyData.add(
            DetailModel(
            thumbnail = "https://img-store.theqoo.net/OgYDrF.webp",
            title = "1",
            date = "",
            channelIcon = "",
            channelName = "",
            description = ""
            ))

        dummyData.add(
            DetailModel(
                thumbnail = "https://img-store.theqoo.net/OgYDrF.webp",
                title = "1",
                date = "",
                channelIcon = "",
                channelName = "",
                description = ""
            ))

        dummyData.add(
            DetailModel(
                thumbnail = "https://img-store.theqoo.net/OgYDrF.webp",
                title = "1",
                date = "",
                channelIcon = "",
                channelName = "",
                description = ""
            ))

    }

    private fun setupAdapter() {
        setupPopularVideoAdapter()
        setupCategoryVideoAdapter()
        setupChannelAdapter()
    }

    private fun setupPopularVideoAdapter() {
        popularVideoAdapter = PopularVideoAdapter(dummyData)
        binding.rvPopularVideoArea.adapter = popularVideoAdapter
    }

    private fun setupCategoryVideoAdapter() {
        categoryVideoAdapter = CategoryVideoAdapter(dummyData)
        binding.rvCategoryArea.adapter = categoryVideoAdapter

    }

    private fun setupChannelAdapter() {
        channelAdapter = ChannelAdapter(dummyData)
        binding.rvChannelArea.adapter = channelAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}