package com.example.ssg_tube.presentaion.detail

import android.content.Intent
import android.os.Bundle
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
    private var detailPageItems: VideoModel? = null


    //데이터 받는 부분
    companion object {
        fun newInstance(videoModel: VideoModel): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable("detailModel", videoModel)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupListeners()
        return binding.root
    }

    private fun setupListeners() {
        binding.apply {
            ivHeart.tag = "unliked" // 초기 상태
            ivHeart.setOnClickListener {
                if (ivHeart.tag == "unliked") {
                    ivHeart.setImageResource(R.drawable.ic_full_heart)
                    ivHeart.tag = "liked"
                    detailPageItems?.liked = true
                    detailPageItems?.let { it -> DBManager.saveData(requireContext(), it.id, it) }
                } else {
                    // 이미 좋아요가 되어 있는 경우
                    ivHeart.setImageResource(R.drawable.ic_blank_heart)
                    ivHeart.tag = "unliked"
                    detailPageItems?.liked = false
                    detailPageItems?.let { it -> DBManager.removeData(requireContext(), it.id) }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //받아온 데이터를 번들에있는걸 꺼냄, shareVideo에 그 데이터 전달ㅎ
        detailPageItems = arguments?.getParcelable<VideoModel>("detailModel")
        detailPageItems?.let {
            detailViewModel.loadChannelData(it)
            bindItem(it)
            shareVideo(it)
        }
        observing()
    }

    private fun observing() {
        detailViewModel.channelDetails.observe(viewLifecycleOwner) { channelDetails ->
            bindItem(channelDetails.first())
        } //채널
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
        binding.ivShare.setOnClickListener {
            val sharedVideoUrl = "${Constants.SHARE_YOUTUBE}${detailPageItems.id}"
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, sharedVideoUrl)
                type = "text/plain"
            }
            startActivity(Intent.createChooser(shareIntent, "Share Video"))
        }
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
        }

    }
}