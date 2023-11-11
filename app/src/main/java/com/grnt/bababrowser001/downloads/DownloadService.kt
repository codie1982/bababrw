package com.grnt.bababrowser001.downloads

import com.grnt.bababrowser001.ext.components
import mozilla.components.browser.state.store.BrowserStore
import mozilla.components.feature.downloads.AbstractFetchDownloadService
import mozilla.components.support.base.android.NotificationsDelegate

class DownloadService :AbstractFetchDownloadService()
{
    override val httpClient by lazy { components.core.client }
    override val store: BrowserStore by lazy { components.core.store }
    override val notificationsDelegate: NotificationsDelegate by lazy { components.notificationsDelegate }
}