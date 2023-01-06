package com.chlqudco.afreecatvtest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chlqudco.afreecatvtest.data.response.Broad
import com.chlqudco.afreecatvtest.databinding.ItemBroadBinding

//페이징 데이터 어댑터
class BroadListPagingAdapter(
    //클릭 이벤트는 프래그먼트에서 처리
    val broadClickListener: (Broad) -> (Unit)
): PagingDataAdapter<Broad, BroadListPagingAdapter.ViewHolder>(DiffCallback) {

    inner class ViewHolder(private val binding: ItemBroadBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(broad: Broad){
            //클릭 리스너
            binding.root.setOnClickListener {
                broadClickListener(broad)
            }

            //썸네일
            Glide.with(binding.ItemBroadCastThumbImageView)
                .load("https:${broad.broadThumb}")
                .into(binding.ItemBroadCastThumbImageView)

            //프로필
            Glide.with(binding.ItemBroadCastProfileImageView)
                .load("https:${broad.profileImg}")
                .into(binding.ItemBroadCastProfileImageView)

            //제목
            binding.ItemBroadCastBroadTitleTextView.text = broad.broadTitle
            //닉네임
            binding.ItemBroadCastNickTextView.text = broad.userNick
            //시청자 수
            binding.ItemBroadCastViewCntTextView.text = broad.totalViewCnt
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pagedModel = getItem(position)
        pagedModel?.let { model ->
            holder.bind(model)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBroadBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object{
        private val DiffCallback = object : DiffUtil.ItemCallback<Broad>(){
            override fun areItemsTheSame(oldItem: Broad, newItem: Broad): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Broad, newItem: Broad): Boolean {
                return oldItem.userId == newItem.userId
            }

        }
    }

}