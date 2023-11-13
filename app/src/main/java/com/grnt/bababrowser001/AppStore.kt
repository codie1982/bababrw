package com.grnt.bababrowser001


import com.grnt.bababrowser001.components.appstate.AppAction
import com.grnt.bababrowser001.components.appstate.AppState
import com.grnt.bababrowser001.components.appstate.AppStoreReducer
import mozilla.components.lib.state.Middleware
import mozilla.components.lib.state.Store

class AppStore(
    initialState: AppState = AppState(),
    middlewares: List<Middleware<AppState, AppAction>> = emptyList(),
) : Store<AppState, AppAction>(initialState, AppStoreReducer::reduce, middlewares)