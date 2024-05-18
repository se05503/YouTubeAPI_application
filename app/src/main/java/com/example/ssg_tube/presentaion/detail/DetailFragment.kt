package com.example.ssg_tube.presentaion.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ssg_tube.Constants
import com.example.ssg_tube.R
import com.example.ssg_tube.data.db.DBManager
import com.example.ssg_tube.databinding.FragmentDetailBinding
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.invisible

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModels()
    private var detailPageItem: VideoModel? = null

    //데이터 받는 부분
    companion object {
        fun newInstance(videoModel: VideoModel): DetailFragment { // 해당 코드 리팩토링이 필요해보입니다.
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable("detailModel", videoModel)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("DetailFragment","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DetailFragment","onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("DetailFragment","onCreateView")
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupViews()
        setupListeners()
        return binding.root
    }

    private fun setupViews() {
        //받아온 데이터를 번들에있는걸 꺼냄, shareVideo에 데이터 전달
        detailPageItem = arguments?.getParcelable("detailModel")
        detailPageItem?.let {
            detailViewModel.loadChannelData(it)
            bindItem(it)
        }
    }

    // 토글 값
    private fun setupListeners() {
        _binding?.apply {
            ivHeart.tag = "unliked" // 초기 상태
            ivHeart.setOnClickListener {
                if (ivHeart.tag == "unliked") {
                    ivHeart.setImageResource(R.drawable.ic_full_heart)
                    ivHeart.tag = "liked"
                    detailPageItem?.liked = true
                    detailPageItem?.let { it -> DBManager.saveData(requireContext(), it.videoId, it) }
                } else {
                    // 이미 좋아요가 되어 있는 경우
                    ivHeart.setImageResource(R.drawable.ic_blank_heart)
                    ivHeart.tag = "unliked"
                    detailPageItem?.liked = false
                    detailPageItem?.let { it -> DBManager.removeData(requireContext(), it.videoId) }
                }
            }

            ivShare.setOnClickListener {
                detailPageItem?.let { it1 -> shareVideo(it1) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("DetailFragment","onViewCreated")
        observing()
    }

    private fun observing() {
        detailViewModel.channelDetails.observe(viewLifecycleOwner) { channelDetails ->
            bindItem(channelDetails)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity)?.invisible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //공유하는 기능
    private fun shareVideo(detailPageItems : VideoModel) {
        val sharedVideoUrl = "${Constants.SHARE_YOUTUBE}${detailPageItems.videoId}"
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, sharedVideoUrl)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share Video"))
    }

    private fun bindItem(videoModel: VideoModel) {
        binding.apply {
            tvDescription.text = videoModel.description
            tvDetailPageVideoTitle.text = videoModel.title
            tvChannelName.text = videoModel.channelName
            tvDetailPageVideoDate.text = videoModel.date
            context?.let {
                Glide.with(it).run {
                    load(videoModel.thumbnail).into(ivThumbnail)
                    load(videoModel.channelIcon).into(ivChannelImage)
                }
            }
            if (ivHeart.tag =="liked") ivHeart.setImageResource(R.drawable.ic_full_heart)
            else if(ivHeart.tag == "unliked") ivHeart.setImageResource(R.drawable.ic_blank_heart)
        }
    }
}