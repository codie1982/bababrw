package com.grnt.bababrowser001.control.topsites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.grnt.bababrowser001.AppStore
import com.grnt.bababrowser001.control.AdapterItem
import com.grnt.bababrowser001.control.TopSiteInteractor
import com.grnt.bababrowser001.feature.model.TopSite

class TopSitesPagerAdapter(
    private val appStore: AppStore,
    private val viewLifecycleOwner: LifecycleOwner,
    private val interactor: TopSiteInteractor,
): ListAdapter<List<TopSite>, TopSiteViewHolder>(TopSiteListDiffCallback)  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSiteViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(TopSiteViewHolder.LAYOUT_ID,parent,false)
        return TopSiteViewHolder(view,appStore,viewLifecycleOwner,interactor)
    }

    override fun onBindViewHolder(holder: TopSiteViewHolder, position: Int) {
        val adapter = holder.binding.topSitesList.adapter as TopSitesAdapter
        adapter.submitList(getItem(position))
    }

}

internal object TopSiteListDiffCallback : DiffUtil.ItemCallback<List<TopSite>>() {
    override fun areItemsTheSame(oldItem: List<TopSite>, newItem: List<TopSite>): Boolean {
        return oldItem.size == newItem.size
    }

    override fun areContentsTheSame(oldItem: List<TopSite>, newItem: List<TopSite>): Boolean {
        return newItem.zip(oldItem).all { (new, old) -> new == old }
    }

    override fun getChangePayload(oldItem: List<TopSite>, newItem: List<TopSite>): Any? {
        val changed = mutableSetOf<Pair<Int, TopSite>>()
        for ((index, item) in newItem.withIndex()) {
            if (oldItem.getOrNull(index) != item) {
                changed.add(Pair(index, item))
            }
        }
        return if (changed.isNotEmpty()) AdapterItem.TopSitePagerPayload(changed) else null
    }
}
