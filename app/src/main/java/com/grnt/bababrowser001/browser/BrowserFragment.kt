package com.grnt.bababrowser001.browser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.ext.components
import com.grnt.bababrowser001.ext.requireComponents
import com.grnt.bababrowser001.search.AwesomeBarWrapper
import mozilla.components.browser.thumbnails.BrowserThumbnails
import mozilla.components.browser.toolbar.BrowserToolbar
import mozilla.components.concept.engine.EngineView
import mozilla.components.feature.readerview.view.ReaderViewControlsBar
import mozilla.components.feature.tabs.toolbar.TabsToolbarFeature
import mozilla.components.feature.toolbar.ToolbarFeature
import mozilla.components.feature.toolbar.WebExtensionToolbarFeature
import mozilla.components.support.base.feature.UserInteractionHandler
import mozilla.components.support.base.feature.ViewBoundFeatureWrapper


/**
 * A simple [Fragment] subclass.
 * Use the [BrowserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BrowserFragment : BaseBrowserFragment(), UserInteractionHandler {
    private val thumbnailsFeature = ViewBoundFeatureWrapper<BrowserThumbnails>()
    private val readerViewFeature = ViewBoundFeatureWrapper<ReaderViewIntegration>()
    private val webExtToolbarFeature = ViewBoundFeatureWrapper<WebExtensionToolbarFeature>()
    private lateinit var toolbarFeature: ToolbarFeature


    private val awesomeBar: AwesomeBarWrapper
        get() = requireView().findViewById(R.id.awesomeBar)
    private val toolbar: BrowserToolbar
        get() = requireView().findViewById(R.id.toolbar)
    private val engineView: EngineView
        get() = requireView().findViewById<View>(R.id.engine) as EngineView
    private val readerViewBar: ReaderViewControlsBar
        get() = requireView().findViewById(R.id.readerViewBar)
    private val readerViewAppearanceButton: FloatingActionButton
        get() = requireView().findViewById(R.id.readerViewAppearanceButton)
    override val shouldUseComposeUI: Boolean
        get() = PreferenceManager.getDefaultSharedPreferences(requireContext()).getBoolean(
            getString(R.string.pref_key_compose_ui),
            false,
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*AwesomeBarFeature(awesomeBar, toolbar, engineView)
            .addSearchProvider(
                requireContext(),
                requireComponents.core.store,
                requireComponents.useCases.searchUseCases.defaultSearch,
                fetchClient = requireComponents.core.client,
                mode = SearchSuggestionProvider.Mode.MULTIPLE_SUGGESTIONS,
                engine = requireComponents.core.engine,
                limit = 5,
                filterExactMatch = true,
            )
            .addSessionProvider(
                resources,
                requireComponents.core.store,
                requireComponents.useCases.tabsUseCases.selectTab,
            )
            .addHistoryProvider(
                requireComponents.core.historyStorage,
                requireComponents.useCases.sessionUseCases.loadUrl,
            )
            .addClipboardProvider(requireContext(), requireComponents.useCases.sessionUseCases.loadUrl)*/

        // We cannot really add a `addSyncedTabsProvider` to `AwesomeBarFeature` coz that would create
        // a dependency on feature-syncedtabs (which depends on Sync).
      /*  awesomeBar.addProviders(
            SyncedTabsStorageSuggestionProvider(
                requireComponents.backgroundServices.syncedTabsStorage,
                requireComponents.useCases.tabsUseCases.addTab,
                requireComponents.core.icons,
            ),
        )*/


        TabsToolbarFeature(
            toolbar = toolbar,
            sessionId = sessionId,
            store = requireComponents.core.store,
            showTabs = ::showTabs,
            lifecycleOwner = this,
        )

        thumbnailsFeature.set(
            feature = BrowserThumbnails(
                requireContext(),
                engineView,
                requireComponents.core.store,
            ),
            owner = this,
            view = view,
        )

        readerViewFeature.set(
            feature = ReaderViewIntegration(
                requireContext(),
                requireComponents.core.engine,
                requireComponents.core.store,
                toolbar,
                readerViewBar,
                readerViewAppearanceButton,
            ),
            owner = this,
            view = view,
        )

        webExtToolbarFeature.set(
            feature = WebExtensionToolbarFeature(
                toolbar,
                requireContext().components.core.store,
            ),
            owner = this,
            view = view,
        )
        engineView.setDynamicToolbarMaxHeight(resources.getDimensionPixelSize(R.dimen.browser_toolbar_height))
    }

    private fun showTabs() {
        // For now we are performing manual fragment transactions here. Once we can use the new
        // navigation support library we may want to pass navigation graphs around.
        println("SHOW TABS")
       /* activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.container, TabsTrayFragment())
            commit()
        }*/
    }


    companion object {
        fun create(sessionId: String? = null) = BrowserFragment().apply {
            arguments = Bundle().apply {
                 putSessionId(sessionId)
            }
        }
    }
}