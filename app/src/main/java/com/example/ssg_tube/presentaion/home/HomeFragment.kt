package com.example.ssg_tube.presentaion.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var popularVideoAdapter: PopularVideoAdapter
    private lateinit var categoryVideoAdapter: CategoryVideoAdapter
    private lateinit var channelAdapter: ChannelAdapter
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var spinner: Spinner

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

        setupSpinner()

        setupAdapter()
        viewModel.popularVideoResponse()
        setupObserve()

    }

    private fun setupAdapter() {
        setupPopularVideoAdapter()
        setupCategoryVideoAdapter()
        setupChannelAdapter()
    }

    private fun setupPopularVideoAdapter() {
        popularVideoAdapter = PopularVideoAdapter(emptyList())
        binding.rvPopularVideoArea.adapter = popularVideoAdapter
    }

    private fun setupCategoryVideoAdapter() {
        categoryVideoAdapter = CategoryVideoAdapter(emptyList())
        binding.rvCategoryArea.adapter = categoryVideoAdapter

    }

    private fun setupChannelAdapter() {
        channelAdapter = ChannelAdapter(emptyList())
        binding.rvChannelArea.adapter = channelAdapter
    }

    private fun setupObserve() {
        viewModel.popularVideo.observe(viewLifecycleOwner, Observer { videos ->
            popularVideoAdapter.updateItem(videos)
        })
    }

    private fun setupSpinner() {
        spinner = binding.spCategory
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}