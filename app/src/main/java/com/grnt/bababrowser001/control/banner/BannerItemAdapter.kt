package com.grnt.bababrowser001.control.banner

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.feature.model.Banner

class BannerItemAdapter(var banners: List<Banner>): RecyclerView.Adapter<BannerItemAdapter.BannerItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerItemViewHolder {
      var view = LayoutInflater.from(parent.context).inflate(BannerPagerViewHolder.LAYOUT_ID,parent,false)
        return BannerItemViewHolder(view)
    }

    override fun getItemCount(): Int {
       return banners.size
    }

    override fun onBindViewHolder(holder: BannerItemViewHolder, position: Int) {

    }
    class BannerItemViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        var image = view.findViewById<ImageView>(R.id.img_banner)

    }
    companion object{
        var LAYOUT_ID = R.layout.banner_item
    }
}
