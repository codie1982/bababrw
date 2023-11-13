package com.grnt.bababrowser001.control

import com.grnt.bababrowser001.feature.model.TopSite

interface ControlController {
    fun handleSelectTopSite(topSite: TopSite, position: Int)
}