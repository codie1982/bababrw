package com.grnt.bababrowser001.control.banner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.grnt.bababrowser001.feature.model.Banner

class BannerPagerAdapter(var bannerList: List<Banner>) : ListAdapter<List<Banner>,BannerPagerViewHolder>(BannerListDiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerPagerViewHolder {
       var view = LayoutInflater.from(parent.context).inflate(BannerPagerViewHolder.LAYOUT_ID,parent,false)
        return BannerPagerViewHolder(view)
    }
    override fun onBindViewHolder(holder: BannerPagerViewHolder, position: Int) {
         //holder.binding.rvBannerList.adapter = BannerItemAdapter(bannerList)
    }
}
internal object BannerListDiffCallback : DiffUtil.ItemCallback<List<Banner>>() {
    override fun areItemsTheSame(oldItem: List<Banner>, newItem: List<Banner>): Boolean {
        return oldItem.size == newItem.size
    }

    override fun areContentsTheSame(oldItem: List<Banner>, newItem: List<Banner>): Boolean {
        return newItem.zip(oldItem).all { (new, old) -> new == old }
    }

}