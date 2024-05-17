package com.example.ssg_tube.presentaion.search

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssg_tube.R
import com.example.ssg_tube.databinding.SearchItemBinding
import com.example.ssg_tube.presentaion.detail.DetailFragment
import com.example.ssg_tube.presentaion.model.VideoModel

class SearchAdapter(private val context: Context):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){

    // 해당 어댑터에서 리사이클러뷰에 뿌려줄 아이템
    var items = ArrayList<VideoModel>()

    // 전체 아이템 삭제(매번 검색을 할 때마다 새로운 데이터가 저장되기 때문)
    // notifyItemInserted()
    fun clearItem() {
        items.clear()
        notifyDataSetChanged() // notifyDataSetChanged()는 모든 뷰를 삭제했다가 다시 갱신하기 때문에 성능저하가 발생할 수 있습니다.
//        notifyItemInserted()
    }

    inner class SearchViewHolder(binding: SearchItemBinding):RecyclerView.ViewHolder(binding.root),View.OnClickListener {
        var tvTitle: TextView = binding.tvTitle
        var tvThumbnail: ImageView = binding.ivThumbnail
        var view: ConstraintLayout = binding.clSearchView

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return // b type 해설 코드 참고했습니다!
            val item = items[position]
            val fragment = DetailFragment.newInstance(item)
            val activity = context as AppCompatActivity // search fragment 가 attach 된 액티비티
            activity.supportFragmentManager.commit {
                replace(R.id.frame_main,fragment)
                setReorderingAllowed(true)
                addToBackStack("")
                Log.d("checkItem","$item")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.tvTitle.text = items[position].title
        Glide.with(context)
            .load(items[position].thumbnail)
            .placeholder(R.drawable.ic_loading) // 로딩중...
            .into(holder.tvThumbnail)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}