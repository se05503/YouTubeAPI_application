package com.example.ssg_tube.presentaion.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ssg_tube.databinding.RvChannelItemBinding
import com.example.ssg_tube.presentaion.model.DetailModel

class ChannelAdapter(private val items: List<DetailModel>) :
    RecyclerView.Adapter<ChannelAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelAdapter.ViewHolder {
        val binding =
            RvChannelItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChannelAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private val binding: RvChannelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DetailModel) {

        }
    }
}