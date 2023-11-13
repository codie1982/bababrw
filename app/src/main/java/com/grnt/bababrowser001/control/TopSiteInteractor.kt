package com.grnt.bababrowser001.control

import com.grnt.bababrowser001.feature.model.TopSite

interface TopSiteInteractor {
    /**
     * Selects the given top site. Called when a user clicks on a top site.
     *
     * @param topSite The top site that was selected.
     * @param position The position of the top site.
     */
    fun onSelectTopSite(topSite: TopSite, position: Int)
}
