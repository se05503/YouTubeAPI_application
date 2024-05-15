package com.example.ssg_tube.presentaion.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ssg_tube.databinding.RvPopularVideoItemBinding
import com.example.ssg_tube.presentaion.model.DetailModel

class PopularVideoAdapter(private val items: List<DetailModel>) : RecyclerView.Adapter<PopularVideoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularVideoAdapter.ViewHolder {
        val binding = RvPopularVideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularVideoAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: RvPopularVideoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailModel) {

        }
    }
}