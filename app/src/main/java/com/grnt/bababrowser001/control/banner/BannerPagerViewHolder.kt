package com.grnt.bababrowser001.control.banner

import android.view.View
import androidx.core.content.res.ResourcesCompat

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.databinding.ComponentBannerPagerBinding
import com.grnt.bababrowser001.feature.model.Banner

class BannerPagerViewHolder(
  val view: View
) : RecyclerView.ViewHolder(view) {
    var binding = ComponentBannerPagerBinding.bind(view)

    fun bind(){
        binding.imgBanner.apply {
            //setImageDrawable(context.getDrawable(R.drawable.hb_banner))
        }

    }
    companion object{
        var LAYOUT_ID = R.layout.component_banner_pager
    }
}