package com.grnt.bababrowser001.components

import android.content.Context
import androidx.preference.PreferenceManager
import com.grnt.bababrowser001.R
import com.grnt.bababrowser001.ext.getPreferenceKey
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import mozilla.components.feature.accounts.FirefoxAccountsAuthFeature
import mozilla.components.feature.app.links.AppLinksInterceptor
import mozilla.components.feature.tabs.TabsUseCases
import mozilla.components.service.fxa.manager.FxaAccountManager

class Services(
    private val context: Context,
    private val accountManager: FxaAccountManager,
    private val tabsUseCases: TabsUseCases,
) {
    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)
    val accountsAuthFeature by lazy {
        FirefoxAccountsAuthFeature(
            accountManager,
            redirectUrl = BackgroundServices.REDIRECT_URL,
        ) {
                _, authUrl ->
            MainScope().launch {
                tabsUseCases.addTab.invoke(authUrl)
            }
        }
    }

    val appLinksInterceptor by lazy {
        AppLinksInterceptor(
            context,
            interceptLinkClicks = true,
            launchInApp = {
                prefs.getBoolean(context.getPreferenceKey(R.string.pref_key_launch_external_app), false)
            },
        )
    }
}