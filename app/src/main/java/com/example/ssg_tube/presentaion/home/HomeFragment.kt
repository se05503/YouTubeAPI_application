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
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.FragmentHomeBinding
import com.example.ssg_tube.presentaion.detail.DetailFragment
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.CategoryType
import com.example.ssg_tube.presentaion.util.OnClickListener
import com.example.ssg_tube.presentaion.util.visible

class HomeFragment : Fragment(), OnClickListener {

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
        viewModel.getPopularVideo()
        setupObserve()
    }

    private fun setupAdapter() {
        setupPopularVideoAdapter()
        setupCategoryVideoAdapter()
        setupChannelAdapter()
    }

    private fun setupPopularVideoAdapter() {
        popularVideoAdapter = PopularVideoAdapter(emptyList(), this)
        binding.rvPopularVideoArea.adapter = popularVideoAdapter
    }

    private fun setupCategoryVideoAdapter() {
        categoryVideoAdapter = CategoryVideoAdapter(emptyList(), this)
        binding.rvCategoryArea.adapter = categoryVideoAdapter
        //..
    }

    private fun setupChannelAdapter() {
        channelAdapter = ChannelAdapter(emptyList())
        binding.rvChannelArea.adapter = channelAdapter
    }

    private fun setupObserve() {
        viewModel.popularVideo.observe(viewLifecycleOwner) { videos ->
            popularVideoAdapter.updateItem(videos)
        }
        viewModel.categoriesVideo.observe(viewLifecycleOwner) { categoryVideos ->
            categoryVideoAdapter.updateItem(categoryVideos)
        }
        viewModel.channel.observe(viewLifecycleOwner) { channels ->
            channelAdapter.updateItem(channels)
        }
    }

    // spinner 공식문서 참조
    private fun setupSpinner() {
        spinner = binding.spCategory
        // ArrayAdapter를 생성
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // OnItemSelectedListener가 인터페이스 형식이라서 유연성을 위해 fragment에 상속하지 않고 object형식으로 변경
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            // 카테고리를 선택 했을때 이벤트 설정
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // parent로 접근하고 Adapter에 접근하고 getItemAtPosition에서 선택된 카테고리의 이름을 가져옴
                val categoryName = parent.getItemAtPosition(position) as String
                // Util패키지의 CategoryType에 categoryName과 일치하는 enum 객체를 가져옴
                // 참고: 현재 Spinner의 Adapter에는 strings에 정의된 Cateogries가 있다.
                val category = CategoryType.from(categoryName)
                category?.let {
                    // 뷰모델의 categoryVideo를 호출해서 해당하는 카테고리의 비디오를 가져옴
                    viewModel.getCategoryVideo(it.categoryId)
                }
            }

            // 카테고리를 선택하지 않았을때 이벤트 설정
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(videoModel: VideoModel) {
        val detailFragment = DetailFragment.newInstance(videoModel)
        parentFragmentManager.beginTransaction()
            .replace(R.id.flMain, detailFragment)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()
    }
}