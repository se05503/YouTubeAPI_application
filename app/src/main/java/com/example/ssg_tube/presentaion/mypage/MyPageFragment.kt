package com.example.ssg_tube.presentaion.mypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.FragmentMyPageBinding
import com.example.ssg_tube.presentaion.detail.DetailFragment
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.visible

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MyPageAdapter

    private val viewModel: MyPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater,container,false)
        setUpView()
        setUpListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun setUpListeners() {
        adapter.setOnItemClickListener(object: MyPageAdapter.OnItemClickListener {
            override fun onItemClick(item: VideoModel, position: Int) {
                val fragment = DetailFragment.newInstance(item)
                parentFragmentManager.beginTransaction() // 이거 나중에 util 쪽으로 빼놔도 좋을 것 같습니다
                    .setCustomAnimations(
                        R.anim.slide_in, // enter
                        R.anim.fade_out, // exit
                        R.anim.fade_in, // popEnter(back stack)
                        R.anim.slide_out // popExit(back stack)
                    )
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .replace(R.id.flMain,fragment)
                    .commit()
            }
        })
    }

    private fun setUpView() {
        adapter = MyPageAdapter()

        with(binding) {
            rvMyPage.layoutManager = GridLayoutManager(requireContext(),2)
            rvMyPage.adapter = adapter
        }

        viewModel.getHeartItems(requireContext())
    }

    private fun observeViewModel() {
        viewModel.likedItems.observe(viewLifecycleOwner) { items ->
            adapter.items = items.toMutableList()
            adapter.notifyDataSetChanged()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        (activity)?.visible()
    }
}


