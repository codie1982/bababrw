package com.grnt.bababrowser001

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.grnt.bababrowser001.components.BackgroundServices
import com.grnt.bababrowser001.components.Push
import com.grnt.bababrowser001.components.Services
import com.grnt.bababrowser001.components.UseCases
import com.grnt.bababrowser001.components.core.Analytics
import com.grnt.bababrowser001.components.core.Core
import mozilla.components.browser.session.storage.SessionStorage
import mozilla.components.feature.customtabs.store.CustomTabsServiceStore
import mozilla.components.support.base.android.NotificationsDelegate

class Components(private val context: Context) {
    val core by lazy { Core(context) }
    val useCases by lazy {
        UseCases(
            context,
            core.engine,
            core.store,
            core.shortcutManager,
        )
    }


    val analytics by lazy { Analytics(context) }

    private val notificationManagerCompat = NotificationManagerCompat.from(context)

    val notificationsDelegate: NotificationsDelegate by lazy {
        NotificationsDelegate(
            notificationManagerCompat,
        )
    }
    val push by lazy { Push(context, analytics.crashReporter) }

    // Background services are initiated eagerly; they kick off periodic tasks and setup an accounts system.
    val backgroundServices by lazy {
        BackgroundServices(
            context,
            push,
            core.lazyHistoryStorage,
            core.lazyRemoteTabsStorage,
            core.lazyLoginsStorage,
        )
    }
    val services by lazy { Services(context, backgroundServices.accountManager, useCases.tabsUseCases) }

}
