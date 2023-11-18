package com.grnt.bababrowser001.components.appstate

import com.grnt.bababrowser001.Mode
import com.grnt.bababrowser001.feature.model.Banner
import com.grnt.bababrowser001.feature.model.TopSite
import mozilla.components.concept.storage.BookmarkNode
import mozilla.components.lib.state.State

/**
 * Value type that represents the state of the tabs tray.
 *
 * @property inactiveTabsExpanded A flag to know if the Inactive Tabs section of the Tabs Tray
 * should be expanded when the tray is opened.
 * @property firstFrameDrawn Flag indicating whether the first frame of the homescreen has been drawn.
 * @property nonFatalCrashes List of non-fatal crashes that allow the app to continue being used.
 * @property collections The list of [TabCollection] to display in the [HomeFragment].
 * @property expandedCollections A set containing the ids of the [TabCollection] that are expanded
 *                               in the [HomeFragment].
 * @property mode The state of the [HomeFragment] UI.
 * @property topSites The list of [TopSite] in the [HomeFragment].
 * @property showCollectionPlaceholder If true, shows a placeholder when there are no collections.
 * @property recentTabs The list of recent [RecentTab] in the [HomeFragment].
 * @property recentSyncedTabState The [RecentSyncedTabState] in the [HomeFragment].
 * @property recentBookmarks The list of recently saved [BookmarkNode]s to show on the [HomeFragment].
 * @property recentHistory The list of [RecentlyVisitedItem]s.
 * @property pocketStories The list of currently shown [PocketRecommendedStory]s.
 * @property pocketStoriesCategories All [PocketRecommendedStory] categories.
 * @property pocketStoriesCategoriesSelections Current Pocket recommended stories categories selected by the user.
 * @property pocketSponsoredStories All [PocketSponsoredStory]s.
 * @property messaging State related messages.
 * @property pendingDeletionHistoryItems The set of History items marked for removal in the UI,
 * awaiting to be removed once the Undo snackbar hides away.
 * Also serves as an in memory cache of all stories mapped by category allowing for quick stories filtering.
 */
data class AppState(
    val topSites: List<TopSite> = emptyList(),
    val banners: List<Banner> = emptyList(),
    val mode:Mode = Mode.Normal
    ) : State