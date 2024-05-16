package com.example.ssg_tube.presentaion.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.example.ssg_tube.databinding.FragmentDetailBinding
import com.example.ssg_tube.presentaion.model.DetailModel
import com.example.ssg_tube.presentaion.ui.detail.util.invisible

class DetailFragment : Fragment() {
    private lateinit var selectedVideoInfo: DetailModel
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    //데이터 받는 부분
    companion object {
        fun newInstance(detailModel: DetailModel): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putParcelable("detailModel", detailModel)
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //받아온 데이터를 번들에있는걸 꺼냄
        val detailModel = arguments?.getParcelable<DetailModel>("detailModel")
        detailModel?.let {
            bindItem(it)
        }
        (activity as? FragmentActivity)?.invisible()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun bindItem(detailModel: DetailModel) {
        binding.apply {
            tvDescription.text = detailModel.description
            tvDetailPageVideoTitle.text = detailModel.title
            tvChannelName.text = detailModel.channelName
            tvDetailPageVideoDate.text = detailModel.date
            context?.let {
                Glide.with(it).run {
                    load(detailModel.thumbnail).into(ivThumbnail)
                    load(detailModel.channelIcon).into(ivChannelImage)
                }
            }
        }

    }
}