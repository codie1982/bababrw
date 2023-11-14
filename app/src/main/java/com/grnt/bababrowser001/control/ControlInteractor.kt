package com.grnt.bababrowser001.control

import com.grnt.bababrowser001.BrowserDirection
import com.grnt.bababrowser001.MainActivity
import com.grnt.bababrowser001.feature.model.TopSite
import mozilla.components.concept.engine.Engine
import mozilla.components.feature.tabs.TabsUseCases
import org.mozilla.geckoview.WebExtension.SessionController

class ControlInteractor(
    private val controller: ControlController
): TopSiteInteractor {
    override fun onSelectTopSite(topSite: TopSite, position: Int) {
        controller.handleSelectTopSite(topSite, position)
    }

}

@Suppress("TooManyFunctions", "LargeClass", "LongParameterList")
class DefaultSessionControlController(
    private val activity: MainActivity,
    private val engine: Engine,
    private val addTabUseCase: TabsUseCases.AddNewTabUseCase,
):ControlController{
    override fun handleSelectTopSite(topSite: TopSite, position: Int) {

        addTabUseCase.invoke(url = topSite.url, selectTab = true, startLoading = true)
        activity.openToBrowser()
    }
}
