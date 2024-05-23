package com.example.ssg_tube.presentaion.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ssg_tube.R
import com.example.ssg_tube.data.repository.YoutubeRepositoryImpl
import com.example.ssg_tube.databinding.FragmentSearchBinding
import com.example.ssg_tube.network.RetroClient
import com.example.ssg_tube.presentaion.SharedViewModel
import com.example.ssg_tube.presentaion.detail.DetailFragment
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.OnClickListener
import com.example.ssg_tube.presentaion.util.visible

class SearchFragment : Fragment(), OnClickListener {

    // 뷰 바인딩
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchAdapter
    private lateinit var layoutManager: GridLayoutManager
    private var lastQuery = "" // 사용자가 검색창에 입력한 값

    private var pastVisibleItems = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    private val apiServiceInstance = RetroClient.youTubeRetrofit
    private val repository by lazy { YoutubeRepositoryImpl(apiServiceInstance) }

    // 뷰 모델
    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(repository)
    }

    private val sharedViewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupListeners()
        sharedViewModel.notifyLiveDataChanged()
        observeViewModel()
    }

    private fun setupViews() {
        layoutManager = GridLayoutManager(requireContext(), 2)

        binding.rvSearch.layoutManager = layoutManager
        adapter = SearchAdapter(this)

        binding.rvSearch.apply {
            adapter = adapter
            itemAnimator = null
            addOnScrollListener(onScrollListener)
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
                viewModel.currentOrder = "relevance" // infinite scroll
                viewModel.getSearch(
                    lastQuery,
                    "relevance"
                ) // relevance: 검색어와의 관련성을 기준으로 리소스를 정렬합니다. 이 매개변수의 기본값입니다.

                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
            }
        }

        binding.btnPopular.setOnClickListener {
            adapter.clearItem()
            viewModel.currentOrder = "viewCount"
            viewModel.getSearch(lastQuery, "viewCount") // viewCount: 리소스가 조회수가 높은 순에서 낮은 순으로 정렬됩니다.
        }

        binding.btnRecent.setOnClickListener {
            adapter.clearItem()
            viewModel.currentOrder = "date"
            viewModel.getSearch(
                lastQuery,
                "date"
            ) // date: 리소스를 만든 날짜를 기준으로 최근 항목부터 시간 순서대로 리소스를 정렬합니다.
        }

        binding.btnRating.setOnClickListener {
            adapter.clearItem()
            viewModel.currentOrder = "rating"
            viewModel.getSearch(lastQuery, "rating") // rating: 높은 평점에서 낮은 평점순으로 리소스가 정렬됩니다.
        }

        binding.fbSearch.setOnClickListener {
            binding.rvSearch.smoothScrollToPosition(0)
        }
    }

    private fun observeViewModel() {
        viewModel.searchResults.observe(viewLifecycleOwner) { items ->
            Log.d("iswork?","work")
            Log.d("iswork?",items.toString())
            adapter.clearItem()
            adapter.items.addAll(items)
            adapter.notifyDataSetChanged()
        }

        sharedViewModel.unlikedItemsId.observe(viewLifecycleOwner) { videoIds ->
            videoIds.forEach { videoId ->
                val targetItem = adapter.items.find { it.videoId == videoId }
                targetItem?.let {
                    it.liked = false
                    val targetItemIndex = adapter.items.indexOf(it)
                    adapter.notifyItemChanged(targetItemIndex)
                }
            }
            sharedViewModel.clearItemUrl()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity)?.visible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(videoModel: VideoModel) {
        val fragment = DetailFragment.newInstance(videoModel)
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in, // enter
                R.anim.fade_out, // exit
                R.anim.fade_in, // popEnter(back stack)
                R.anim.slide_out // popExit(back stack)
            )
            .replace(R.id.flMain, fragment)
            .addToBackStack(null)
            .setReorderingAllowed(true)
            .commit()
    }

    private var onScrollListener: RecyclerView.OnScrollListener =
        object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                visibleItemCount = layoutManager.childCount
                totalItemCount = layoutManager.itemCount
                pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                if (binding.fbSearch.visibility != View.VISIBLE && dy > 0) // dy>0 : 아래로 스크롤 하는 경우, dy<0: 위로 스크롤 하는 경우
                    binding.fbSearch.show()

                if (!recyclerView.canScrollVertically(-1)) { // 최상단 기준(-1) 위쪽으로 스크롤이 안될 때 플로팅 버튼을 숨긴다.
                    binding.fbSearch.hide()
                }

                if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                    viewModel.getNextPage(lastQuery)
                }
            }
        }
}