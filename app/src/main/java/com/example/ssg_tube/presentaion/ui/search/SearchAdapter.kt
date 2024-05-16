package com.example.ssg_tube.presentaion.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.SearchItemBinding
import com.example.ssg_tube.presentaion.model.SearchItemModel

class SearchAdapter(private val context: Context):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){

    // 해당 어댑터에서 리사이클러뷰에 뿌려줄 아이템
    var items = ArrayList<SearchItemModel>()

    // 전체 아이템 삭제(매번 검색을 할 때마다 새로운 데이터가 저장되기 때문)
    fun clearItem() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(binding: SearchItemBinding):RecyclerView.ViewHolder(binding.root) {
        var tvTitle: TextView = binding.tvTitle
        var tvThumbnail: ImageView = binding.ivThumbnail
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tvTitle.text = items[position].title
        Glide.with(context)
            .load(items[position].url)
            .placeholder(R.drawable.ic_loading) // 로딩중...
            .into(holder.tvThumbnail)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}