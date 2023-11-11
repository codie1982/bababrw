package com.grnt.bababrowser001.customtabs

import com.grnt.bababrowser001.ext.components
import mozilla.components.concept.engine.Engine
import mozilla.components.feature.customtabs.AbstractCustomTabsService
import mozilla.components.feature.customtabs.store.CustomTabsServiceStore

class CustomTabsService : AbstractCustomTabsService() {
    override val customTabsServiceStore: CustomTabsServiceStore by lazy { components.core.customTabsStore }
    override val engine: Engine by lazy { components.core.engine }
}