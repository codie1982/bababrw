package com.grnt.bababrowser001.components.appstate

import com.grnt.bababrowser001.feature.model.TopSite
import mozilla.components.lib.state.Action

sealed class AppAction : Action {
    data class TopSitesChange(val topSites: List<TopSite>) : AppAction()
}