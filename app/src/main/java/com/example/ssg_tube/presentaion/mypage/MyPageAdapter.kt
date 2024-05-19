package com.example.ssg_tube.presentaion.mypage

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.SearchItemBinding
import com.example.ssg_tube.presentaion.detail.DetailFragment
import com.example.ssg_tube.presentaion.model.VideoModel

class MyPageAdapter(private val context: Context): RecyclerView.Adapter<MyPageAdapter.MyPageViewHolder>() {

    // 좋아요한 아이템들
    var items = mutableListOf<VideoModel>()

    inner class MyPageViewHolder(val binding: SearchItemBinding, val context: Context):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        var view = binding.clSearchView

        init {
            view.setOnClickListener(this)
        }

        fun bind(item: VideoModel) {
            binding.apply {
                Glide.with(context)
                    .load(item.thumbnail)
                    .into(ivThumbnail)
                tvTitle.text = item.title
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]
            val fragment = DetailFragment.newInstance(item)
            val activity = context as AppCompatActivity // my page fragment 가 attach 된 메인 액티비티
            // 현재 상황: 마이 페이지에서 아이템을 클릭 했을 때 디테일 프래그먼트로 이동한다
            activity.supportFragmentManager.commit {
                replace(R.id.flMain,fragment)
                setReorderingAllowed(true)
                addToBackStack(null)
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