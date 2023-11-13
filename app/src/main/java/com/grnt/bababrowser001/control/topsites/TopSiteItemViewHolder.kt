package com.grnt.bababrowser001.control.topsites

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.grnt.bababrowser001.AppStore
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.control.TopSiteInteractor
import com.grnt.bababrowser001.databinding.TopSiteItemBinding
import com.grnt.bababrowser001.feature.model.TopSite
import mozilla.components.lib.state.ext.flowScoped

@SuppressLint("ClickableViewAccessibility")
class TopSiteItemViewHolder(
    view: View,
    appStore: AppStore,
    private val viewLifecycleOwner: LifecycleOwner,
    private val interactor: TopSiteInteractor,

    ) :ViewHolder(view){

    private val binding = TopSiteItemBinding.bind(view)


    init {
        appStore.flowScoped(viewLifecycleOwner){

        }
    }
    fun bind(topSite: TopSite, position: Int){
        itemView.setOnClickListener {
            interactor.onSelectTopSite(topSite, position)
        }
        binding.topSiteTitle.text = topSite.title
        binding.topSiteSubtitle.visibility = View.VISIBLE
    }

    companion object {
        var LAYOUT_ID = R.layout.top_site_item
    }
}
