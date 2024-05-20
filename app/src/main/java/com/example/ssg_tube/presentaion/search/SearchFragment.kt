package com.example.ssg_tube.presentaion.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ssg_tube.databinding.FragmentSearchBinding
import com.example.ssg_tube.network.RetroClient
import com.example.ssg_tube.presentaion.SharedViewModel
import com.example.ssg_tube.presentaion.detail.DetailFragment
import com.example.ssg_tube.presentaion.util.invisible
import com.example.ssg_tube.presentaion.util.visible

class SearchFragment : Fragment() {

    // 뷰 바인딩
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context
    private lateinit var adapter: SearchAdapter
    private lateinit var layoutManager: GridLayoutManager
    private var lastQuery = "" // 사용자가 검색창에 입력한 값
    private val apiServiceInstance = RetroClient.youTubeRetrofit

    // 뷰 모델
    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(apiServiceInstance)
    }

    val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("Search fragmnet","onAttach")
        mContext = context // Glide 설정하면 어댑터에 넘겨줘야 함
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Search fragmnet","onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Search fragmnet","onCreateView")
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupViews()
        setupListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Search fragmnet","onViewCreated")
        observeViewModel()
    }

    private fun setupViews() {
        layoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvSearch.layoutManager = layoutManager
        adapter = SearchAdapter(mContext)

        binding.apply {
            rvSearch.adapter = adapter
            rvSearch.itemAnimator = null // 진혁님 피드백
            // ... 나중에 기능 추가
        }
    }

    private fun setupListeners() {
        binding.btnSearch.setOnClickListener {
            if (binding.etSearch.text.toString() == "")
                Toast.makeText(requireContext(), "검색어를 입력하세요!", Toast.LENGTH_SHORT).show()
            else {
                // 사용자가 검색어를 입력한 경우
                adapter.clearItem() // 검색어를 저장하기 전에 일단 전 데이터 삭제하기
                lastQuery = binding.etSearch.text.toString()
                viewModel.videoResults(lastQuery,"relevance") // relevance: 검색어와의 관련성을 기준으로 리소스를 정렬합니다. 이 매개변수의 기본값입니다.
            }
        }

        binding.btnPopular.setOnClickListener {
            adapter.clearItem()
            viewModel.videoResults(lastQuery, "viewCount") // viewCount: 리소스가 조회수가 높은 순에서 낮은 순으로 정렬됩니다.
        }

        binding.btnRecent.setOnClickListener {
            adapter.clearItem()
            viewModel.videoResults(lastQuery,"date") // date: 리소스를 만든 날짜를 기준으로 최근 항목부터 시간 순서대로 리소스를 정렬합니다.
        }

        binding.btnRating.setOnClickListener {
            adapter.clearItem()
            viewModel.videoResults(lastQuery,"rating") // rating: 높은 평점에서 낮은 평점순으로 리소스가 정렬됩니다.
        }
    }

    private fun observeViewModel() {
        viewModel.searchResults.observe(viewLifecycleOwner) { items ->
            adapter.items.addAll(items)
            adapter.notifyDataSetChanged()
        }

        sharedViewModel.unlikedItemsUrl.observe(viewLifecycleOwner) { videoIds ->
            videoIds.forEach { videoId ->
                val targetItem = adapter.items.find { it.videoId == videoId }
                targetItem?.let {
                    it.liked = false
                    val targetItemIndex = adapter.items.indexOf(it)
                    adapter.notifyItemChanged(targetItemIndex)
                }
//                sharedViewModel.clearItemUrl()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("Search fragmnet","onResume")
        (activity)?.visible() // 네비게이션 바 안보이는 현상 해결
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Search fragmnet","onDestroyView")
        _binding = null
    }
}