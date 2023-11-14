package com.grnt.bababrowser001.tabs

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.browser.BrowserFragment
import com.grnt.bababrowser001.ext.components
import com.grnt.bababrowser001.ext.requireComponents
import mozilla.components.browser.state.state.TabSessionState
import mozilla.components.browser.tabstray.DefaultTabViewHolder
import mozilla.components.browser.tabstray.TabsAdapter
import mozilla.components.browser.tabstray.TabsTray
import mozilla.components.browser.tabstray.ViewHolderProvider
import mozilla.components.browser.thumbnails.loader.ThumbnailLoader
import mozilla.components.feature.tabs.tabstray.TabsFeature
import mozilla.components.support.base.feature.UserInteractionHandler


/**
 * A simple [Fragment] subclass.
 * Use the [TabsTrayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TabsTrayFragment : Fragment(),UserInteractionHandler {
    private var tabsFeature: TabsFeature? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val trayAdapter = createAndSetupTabsTray(requireContext())
        tabsFeature = TabsFeature(
            trayAdapter,
            requireComponents.core.store,
            ::closeTabsTray,
        )

        val tabsToolbar: TabsToolbar = view.findViewById(R.id.tabsToolbar)
        tabsToolbar.initialize(tabsFeature) { }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return  inflater.inflate(R.layout.fragment_tabs_tray, container, false)
    }

    private fun createAndSetupTabsTray(requireContext: Context): TabsTray {
        val layoutManager = LinearLayoutManager(requireContext)
        val thumbnailLoader = ThumbnailLoader(requireContext.components.core.thumbnailStorage)
        val viewHolderProvider: ViewHolderProvider = { viewGroup ->
            val view = LayoutInflater.from(context)
                .inflate(R.layout.browser_tabstray_item, viewGroup, false)

            DefaultTabViewHolder(view, thumbnailLoader)
        }
        val tabsAdapter = TabsAdapter(
            thumbnailLoader = thumbnailLoader,
            viewHolderProvider = viewHolderProvider,
            delegate = object : TabsTray.Delegate {
                override fun onTabSelected(tab: TabSessionState, source: String?) {
                    requireComponents.useCases.tabsUseCases.selectTab(tab.id)
                    closeTabsTray()
                }

                override fun onTabClosed(tab: TabSessionState, source: String?) {
                    requireComponents.useCases.tabsUseCases.removeTab(tab.id)
                }
            },
        )
        val tabsTray = requireView().findViewById<RecyclerView>(R.id.tabsTray)
        tabsTray.layoutManager = layoutManager
        tabsTray.adapter = tabsAdapter

        return tabsAdapter
    }

    override fun onBackPressed(): Boolean {
        closeTabsTray()
        return true
    }
    private fun closeTabsTray() {
      findNavController().navigate(R.id.action_tabsTrayFragment_to_homeFragment)
    }
    override fun onStart() {
        super.onStart()

        tabsFeature?.start()
    }

    override fun onStop() {
        super.onStop()

        tabsFeature?.stop()
    }


    companion object {
    }
}