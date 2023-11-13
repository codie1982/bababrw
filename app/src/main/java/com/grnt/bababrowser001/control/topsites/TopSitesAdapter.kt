package com.grnt.bababrowser001.control.topsites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.grnt.bababrowser001.AppStore
import com.grnt.bababrowser001.control.TopSiteInteractor
import com.grnt.bababrowser001.feature.model.TopSite

class TopSitesAdapter(
    private val appStore: AppStore,
    private val viewLifecycleOwner: LifecycleOwner,
    private val interactor: TopSiteInteractor,
) : ListAdapter<TopSite, TopSiteItemViewHolder>(TopSitesDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopSiteItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(TopSiteItemViewHolder.LAYOUT_ID,parent,false)
        return TopSiteItemViewHolder(view,appStore, viewLifecycleOwner,interactor)
    }

    override fun onBindViewHolder(holder: TopSiteItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }


    internal object TopSitesDiffCallback : DiffUtil.ItemCallback<TopSite>() {
        override fun areItemsTheSame(oldItem: TopSite, newItem: TopSite) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TopSite, newItem: TopSite) =
            oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.url == newItem.url

        override fun getChangePayload(oldItem: TopSite, newItem: TopSite): Any? {
            return if (oldItem.id == newItem.id && oldItem.url == newItem.url && oldItem.title != newItem.title) {
                newItem
            } else {
                null
            }
        }
    }


}
