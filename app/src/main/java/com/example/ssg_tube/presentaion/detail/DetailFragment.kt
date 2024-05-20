package com.example.ssg_tube.presentaion.detail

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ssg_tube.Constants
import com.example.ssg_tube.R
import com.example.ssg_tube.data.db.DBManager
import com.example.ssg_tube.databinding.FragmentDetailBinding
import com.example.ssg_tube.presentaion.SharedViewModel
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.invisible

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailViewModel: DetailViewModel by viewModels()
    private var detailPageItem: VideoModel? = null

    private val sharedViewModel by activityViewModels<SharedViewModel>()

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.apply {
            ivHeart.setOnClickListener {
                if (detailPageItem?.liked == true) {
                    Toast.makeText(requireContext(), "해당 동영상이 보관함에서 삭제되었습니다.", Toast.LENGTH_SHORT)
                        .show() // 나중에 확장 함수로 빼기
                    ivHeart.setImageResource(R.drawable.ic_blank_heart)
                    detailPageItem?.liked = false
                    detailPageItem?.let {
                        DBManager.removeData(requireContext(), it.videoId)
                        sharedViewModel.addItemId(it.videoId)
                    }
                } else {
                    Toast.makeText(requireContext(), "해당 동영상이 보관함에 추가되었습니다.", Toast.LENGTH_SHORT)
                        .show() // 나중에 확장 함수로 빼기
                    ivHeart.setImageResource(R.drawable.ic_full_heart)
                    detailPageItem?.liked = true
                    detailPageItem?.let {
                        DBManager.saveData(requireContext(), it.videoId, it)
                        sharedViewModel.deleteItemId(it.videoId)
                    }
                }
            }

            ivShare.setOnClickListener {
                detailPageItem?.let { it1 -> shareVideo(it1) }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
    private fun shareVideo(detailPageItems: VideoModel) {
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
            if (detailPageItem?.liked == true) ivHeart.setImageResource(R.drawable.ic_full_heart)
            else ivHeart.setImageResource(R.drawable.ic_blank_heart)
        }
    }
}