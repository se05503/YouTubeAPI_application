package com.example.ssg_tube.presentaion.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.databinding.RvCategoryVideoItemBinding
import com.example.ssg_tube.presentaion.model.CategoryInfo
import com.example.ssg_tube.presentaion.model.VideoModel

class CategoryVideoAdapter(private var items: List<CategoryInfo>) :
    RecyclerView.Adapter<CategoryVideoAdapter.ViewHolder>() {

    fun updateItem(newItems: List<CategoryInfo>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            RvCategoryVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: RvCategoryVideoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryInfo) {
            binding.apply {
                Glide.with(ivArea.context)
                    .load(item.thumbnail)
                    .into(ivArea)
                tvTitle.text = item.title
            }

        }
    }
}