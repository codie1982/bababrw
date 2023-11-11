package com.grnt.bababrowser001.tabs

import android.content.Context
import androidx.lifecycle.ProcessLifecycleOwner
import com.grnt.bababrowser001.ext.components
import mozilla.components.concept.sync.AccountObserver
import mozilla.components.concept.sync.AuthType
import mozilla.components.concept.sync.OAuthAccount
import mozilla.components.service.fxa.manager.FxaAccountManager

class SyncedTabsIntegration(
    private val context: Context,
    private val accountManager: FxaAccountManager,
) {
    fun launch() {
        val accountObserver = SyncedTabsAccountObserver(context)

        accountManager.register(
            accountObserver,
            owner = ProcessLifecycleOwner.get(),
            autoPause = true,
        )
    }
}

internal class SyncedTabsAccountObserver(private val context: Context) : AccountObserver {
    override fun onAuthenticated(account: OAuthAccount, authType: AuthType) {
        context.components.backgroundServices.syncedTabsStorage.start()
    }

    override fun onLoggedOut() {
        context.components.backgroundServices.syncedTabsStorage.stop()
    }
}