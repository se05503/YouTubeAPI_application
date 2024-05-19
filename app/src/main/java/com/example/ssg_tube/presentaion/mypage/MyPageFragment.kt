package com.example.ssg_tube.presentaion.mypage

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ssg_tube.databinding.FragmentMyPageBinding
import com.example.ssg_tube.presentaion.util.visible

class MyPageFragment : Fragment() {

    private var _binding: FragmentMyPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context
    private lateinit var adapter: MyPageAdapter
    private val viewModel: MyPageViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyPageBinding.inflate(inflater,container,false)
        setUpView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun setUpView() {
        adapter = MyPageAdapter(mContext)

        _binding?.apply {
            rvMyPage.layoutManager = GridLayoutManager(requireContext(),2)
            rvMyPage.adapter = adapter
        }

        viewModel.getHeartItems(mContext)
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