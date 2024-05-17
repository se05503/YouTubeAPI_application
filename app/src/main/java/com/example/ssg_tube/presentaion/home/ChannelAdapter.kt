package com.example.ssg_tube.presentaion.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.databinding.RvChannelItemBinding
import com.example.ssg_tube.presentaion.model.ChannelInfo

class ChannelAdapter(private var items: List<ChannelInfo>) :
    RecyclerView.Adapter<ChannelAdapter.ViewHolder>() {

    fun updateItem(newItems: List<ChannelInfo>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RvChannelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: RvChannelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChannelInfo) {
            binding.apply {
                Glide.with(ivArea.context)
                    .load(item.thumbnail)
                    .into(ivArea)
            }
        }
    }
}