package com.grnt.bababrowser001.control

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grnt.bababrowser001.Mode
import com.grnt.bababrowser001.components.appstate.AppState
import com.grnt.bababrowser001.ext.components
import com.grnt.bababrowser001.ext.settings
import com.grnt.bababrowser001.feature.model.Banner
import com.grnt.bababrowser001.feature.model.TopSite
import com.grnt.bababrowser001.utils.Settings

// This method got a little complex with the addition of the tab tray feature flag
// When we remove the tabs from the home screen this will get much simpler again.
@Suppress("ComplexMethod", "LongParameterList")
@VisibleForTesting
internal fun normalModeAdapterItems(
    settings: Settings,
    topSites: List<TopSite>,
    banners: List<Banner>,
): List<AdapterItem> {
    val items = mutableListOf<AdapterItem>()
    //var shouldShowCustomizeHome = false

    items.add(AdapterItem.TopSitePager(topSites))
    items.add(AdapterItem.BannerPager(banners))
    settings.toString()
    return items
}
private fun AppState.toAdapterList(settings: Settings): List<AdapterItem> = when (mode) {
    is Mode.Normal -> normalModeAdapterItems(
        settings,
        topSites,
        banners

    )
    else -> throw IllegalStateException()
}
class ControlView(
    containerView: View,
    viewLifecycleOwner: LifecycleOwner,
    interactor: ControlInteractor,
) {

    val view: RecyclerView = containerView as RecyclerView

    private val controlAdapter = ControlAdapter(
        interactor,
        viewLifecycleOwner,
        containerView.context.components,
    )


    init {
        view.apply {
            adapter = controlAdapter
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true)
        }
    }
    fun update(state: AppState) {
        controlAdapter.submitList(state.toAdapterList(view.context.settings(),))
    }
}

