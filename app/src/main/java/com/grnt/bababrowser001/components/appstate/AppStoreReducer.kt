package com.grnt.bababrowser001.components.appstate

internal object AppStoreReducer {
    fun reduce(state: AppState, action: AppAction): AppState {
        when(action){
            is AppAction.TopSitesChange -> state.copy(topSites = action.topSites)
        }
        return state
    }
}