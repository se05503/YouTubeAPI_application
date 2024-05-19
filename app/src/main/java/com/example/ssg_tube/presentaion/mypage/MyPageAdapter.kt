package com.example.ssg_tube.presentaion.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.databinding.SearchItemBinding
import com.example.ssg_tube.presentaion.detail.DetailFragment
import com.example.ssg_tube.presentaion.model.VideoModel

class MyPageAdapter(private val context: Context): RecyclerView.Adapter<MyPageAdapter.MyPageViewHolder>() {

    // 좋아요한 아이템들
    var items = mutableListOf<VideoModel>()

    interface OnItemClickListener {
        fun onItemClick(item: VideoModel, position: Int)
    }

    private var clickListener: OnItemClickListener?= null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.clickListener = listener
    }

    inner class MyPageViewHolder(val binding: SearchItemBinding, val context: Context):RecyclerView.ViewHolder(binding.root) {
        var view = binding.clSearchView

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
        holder.view.setOnClickListener {
            clickListener?.onItemClick(items[position],position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}