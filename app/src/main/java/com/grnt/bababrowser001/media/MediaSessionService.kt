package com.grnt.bababrowser001.media

import com.grnt.bababrowser001.ext.components
import mozilla.components.browser.state.store.BrowserStore
import mozilla.components.concept.base.crash.CrashReporting
import mozilla.components.feature.media.service.AbstractMediaSessionService
import mozilla.components.support.base.android.NotificationsDelegate

class MediaSessionService : AbstractMediaSessionService() {
    override val store: BrowserStore by lazy { components.core.store }
    override val crashReporter: CrashReporting  by lazy { components.analytics.crashReporter }
    override val notificationsDelegate: NotificationsDelegate by lazy { components.notificationsDelegate }
}