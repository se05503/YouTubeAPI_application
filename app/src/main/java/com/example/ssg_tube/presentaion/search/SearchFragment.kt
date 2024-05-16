package com.example.ssg_tube.presentaion.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ssg_tube.databinding.FragmentSearchBinding
import com.example.ssg_tube.network.RetroClient

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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context // Glide 설정하면 어댑터에 넘겨줘야 함
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupViews()
        setupListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                viewModel.videoResults(lastQuery) // 오류 코드
            }
        }
    }

    private fun observeViewModel() {
        viewModel.searchResults.observe(viewLifecycleOwner) { items ->
            adapter.items.addAll(items)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}