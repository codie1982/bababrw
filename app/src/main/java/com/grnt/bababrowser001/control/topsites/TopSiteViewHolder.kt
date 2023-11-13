package com.grnt.bababrowser001.control.topsites

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.grnt.bababrowser001.AppStore
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.control.TopSiteInteractor
import com.grnt.bababrowser001.databinding.ComponentTopSitesBinding
import com.grnt.bababrowser001.feature.model.TopSite
import com.grnt.bababrowser001.utils.AccessibilityGridLayoutManager

class TopSiteViewHolder(
    view: View,
    appStore: AppStore,
    viewLifecycleOwner: LifecycleOwner,
    interactor: TopSiteInteractor,
):RecyclerView.ViewHolder(view) {

    private val topSitesAdapter = TopSitesAdapter(appStore, viewLifecycleOwner, interactor)
    val binding = ComponentTopSitesBinding.bind(view)

    init {
        val gridLayoutManager =
            AccessibilityGridLayoutManager(view.context, SPAN_COUNT)

        binding.topSitesList.apply {
            adapter = topSitesAdapter
            layoutManager = gridLayoutManager
        }
    }
    fun bind(topSites: List<TopSite>) {
        topSitesAdapter.submitList(topSites)
    }
    companion object {
        var LAYOUT_ID = R.layout.component_top_sites
        const val SPAN_COUNT = 4
    }
}