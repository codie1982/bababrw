package com.grnt.bababrowser001.browser

import android.content.Context
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.ext.components
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import mozilla.components.browser.domains.autocomplete.ShippedDomainsProvider
import mozilla.components.browser.state.selector.selectedTab
import mozilla.components.browser.state.store.BrowserStore
import mozilla.components.browser.storage.sync.PlacesHistoryStorage
import mozilla.components.browser.toolbar.BrowserToolbar
import mozilla.components.browser.toolbar.display.DisplayToolbar
import mozilla.components.feature.pwa.WebAppUseCases
import mozilla.components.feature.session.SessionUseCases
import mozilla.components.feature.tabs.TabsUseCases
import mozilla.components.feature.toolbar.ToolbarAutocompleteFeature
import mozilla.components.feature.toolbar.ToolbarFeature
import mozilla.components.lib.state.ext.flow
import mozilla.components.support.base.feature.LifecycleAwareFeature
import mozilla.components.support.base.feature.UserInteractionHandler

class ToolbarIntegration(
    private val context: Context,
    toolbar: BrowserToolbar,
    historyStorage: PlacesHistoryStorage,
    store: BrowserStore,
    private val sessionUseCases: SessionUseCases,
    private val tabsUseCases: TabsUseCases,
    private val webAppUseCases: WebAppUseCases,
    sessionId: String? = null,
) : LifecycleAwareFeature,UserInteractionHandler {
    private val shippedDomainsProvider = ShippedDomainsProvider().also {
        it.initialize(context)
    }

    private val scope = MainScope()

    init {
        toolbar.display.indicators = listOf(
            DisplayToolbar.Indicators.SECURITY,
            DisplayToolbar.Indicators.TRACKING_PROTECTION
        )
        toolbar.display.displayIndicatorSeparator = true
        //toolbar.display.menuController = menuController

        toolbar.display.hint = context.getString(R.string.toolbar_hint)
        toolbar.edit.hint = context.getString(R.string.toolbar_hint)

        ToolbarAutocompleteFeature(toolbar).apply {
            updateAutocompleteProviders(
                listOf(historyStorage, shippedDomainsProvider),
            )
        }

        toolbar.display.setUrlBackground(
            ResourcesCompat.getDrawable(context.resources, R.drawable.url_background, context.theme),
        )

        scope.launch {
            store.flow()
                .map { state -> state.selectedTab }
                .distinctUntilChanged()
                .collect { tab ->
                    tab?.content?.url
                   // menuController.submitList(menuItems(tab))
                }
        }
    }

    private val toolbarFeature: ToolbarFeature = ToolbarFeature(
        toolbar,
        context.components.core.store,
        context.components.useCases.sessionUseCases.loadUrl,
        { searchTerms ->
            context.components.useCases.searchUseCases.defaultSearch.invoke(
                searchTerms = searchTerms,
                searchEngine = null,
                parentSessionId = null,
            )
        },
        sessionId,
    )

    override fun onBackPressed(): Boolean {
        return toolbarFeature.onBackPressed()
    }

    override fun start() {
        toolbarFeature.start()
    }

    override fun stop() {
        toolbarFeature.stop()
    }
}