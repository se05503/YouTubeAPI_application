package com.example.ssg_tube.presentaion.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.databinding.SearchItemBinding
import com.example.ssg_tube.presentaion.model.VideoModel

class MyPageAdapter(private val context: Context): RecyclerView.Adapter<MyPageAdapter.MyPageViewHolder>() {

    // 좋아요한 아이템들
    var items = mutableListOf<VideoModel>()

    class MyPageViewHolder(val binding: SearchItemBinding, val context: Context):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoModel) {
            binding.apply {
                Glide.with(context)
                    .load(item.thumbnail)
                    .into(ivThumbnail)
                tvTitle.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyPageViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}