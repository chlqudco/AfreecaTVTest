package com.chlqudco.afreecatvtest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chlqudco.afreecatvtest.data.response.Broad
import com.chlqudco.afreecatvtest.databinding.ItemBroadBinding

class BroadListAdapter(
    val broadClickListener: (Broad) -> (Unit)
): RecyclerView.Adapter<BroadListAdapter.ViewHolder>() {

    var broadList: List<Broad> = emptyList()


    inner class ViewHolder(private val binding: ItemBroadBinding ): RecyclerView.ViewHolder(binding.root){
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

            //시청자
            binding.ItemBroadCastViewCntTextView.text = broad.totalViewCnt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBroadBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(broadList[position])
    }

    override fun getItemCount(): Int {
        return broadList.size
    }
}