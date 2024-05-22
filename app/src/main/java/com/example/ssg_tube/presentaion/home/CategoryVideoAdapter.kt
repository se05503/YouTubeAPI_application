package com.example.ssg_tube.presentaion.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.RvCategoryVideoItemBinding
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.OnClickListener

class CategoryVideoAdapter(
    private var items: List<VideoModel>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<CategoryVideoAdapter.ViewHolder>() {

    fun updateItem(newItems: List<VideoModel>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvCategoryVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: RvCategoryVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoModel, onClickListener: OnClickListener) {
            binding.apply {
                Glide.with(ivArea.context)
                    .load(item.thumbnail)
                    // item.thumbnail이 로드되지 않을 시 .error 이미지를 보여줌
                    .error(R.drawable.ic_video_error)
                    .into(ivArea)
                tvTitle.text = item.title

                ivArea.setOnClickListener {
                    onClickListener.onClick(item)
                }
            }
        }
    }
}