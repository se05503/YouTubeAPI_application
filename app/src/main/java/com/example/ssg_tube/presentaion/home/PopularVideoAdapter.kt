package com.example.ssg_tube.presentaion.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.databinding.RvPopularVideoItemBinding
import com.example.ssg_tube.presentaion.model.VideoModel

class PopularVideoAdapter(private val items: List<VideoModel>) :
    RecyclerView.Adapter<PopularVideoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            RvPopularVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: RvPopularVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoModel) {
            binding.apply {
                Glide.with(ivArea.context)
                    .load(item.thumbnail)
                    .into(ivArea)
                tvTitle.text = item.title
            }
        }
    }
}