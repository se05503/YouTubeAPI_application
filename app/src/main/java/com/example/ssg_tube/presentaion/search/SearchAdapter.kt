package com.example.ssg_tube.presentaion.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.SearchItemBinding
import com.example.ssg_tube.presentaion.model.VideoModel
import com.example.ssg_tube.presentaion.util.OnClickListener

class SearchAdapter(private val onClickListener: OnClickListener): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){

    // 해당 어댑터에서 리사이클러뷰에 뿌려줄 아이템)
    val items = ArrayList<VideoModel>()

    // 전체 아이템 삭제(매번 검색을 할 때마다 새로운 데이터가 저장되기 때문)
    fun clearItem() {
        items.clear()
        notifyDataSetChanged() // notifyDataSetChanged()는 모든 뷰를 삭제했다가 다시 갱신하기 때문에 성능저하가 발생할 수 있습니다.
    }

    class SearchViewHolder(binding: SearchItemBinding): RecyclerView.ViewHolder(binding.root) {
        var tvTitle: TextView = binding.tvTitle
        var tvThumbnail: ImageView = binding.ivThumbnail
        var view: ConstraintLayout = binding.clSearchView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tvTitle.text = items[position].title
        Glide.with(holder.tvThumbnail.context)
            .load(items[position].thumbnail)
            .placeholder(R.drawable.ic_loading)
            .into(holder.tvThumbnail)
        holder.view.setOnClickListener {
            onClickListener.onClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}