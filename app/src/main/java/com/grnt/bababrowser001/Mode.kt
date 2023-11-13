package com.grnt.bababrowser001

import com.grnt.bababrowser001.browser.manager.BrowsingMode

sealed class Mode {
    object Normal : Mode()
    object Private : Mode()

    companion object {
        fun fromBrowsingMode(browsingMode: BrowsingMode) = when (browsingMode) {
            BrowsingMode.Normal -> Normal
            BrowsingMode.Private -> Private
        }
    }
}