package com.grnt.bababrowser001

import android.content.Context
import com.grnt.bababrowser001.ext.isCrashReportActive
import mozilla.components.browser.engine.gecko.GeckoEngine
import mozilla.components.browser.engine.gecko.fetch.GeckoViewFetchClient
import mozilla.components.browser.engine.gecko.glean.GeckoAdapter
import mozilla.components.concept.engine.DefaultSettings
import mozilla.components.concept.engine.Engine
import mozilla.components.concept.fetch.Client
import mozilla.components.feature.webcompat.WebCompatFeature
import mozilla.components.lib.crash.handler.CrashHandlerService
import org.mozilla.geckoview.GeckoRuntime
import org.mozilla.geckoview.GeckoRuntimeSettings

object EngineProvider {

    private var runtime: GeckoRuntime? = null

    @Synchronized
    fun getOrCreateRuntime(context: Context): GeckoRuntime {
        if (runtime == null) {
            val builder = GeckoRuntimeSettings.Builder()

            if (isCrashReportActive) {
                builder.crashHandler(CrashHandlerService::class.java)
            }

            // Allow for exfiltrating Gecko metrics through the Glean SDK.
            builder.telemetryDelegate(GeckoAdapter())

            // About config it's no longer enabled by default
            builder.aboutConfigEnabled(true)

            runtime = GeckoRuntime.create(context,builder.build())
        }

        return runtime!!
    }

    fun createEngine(context: Context, defaultSettings: DefaultSettings): Engine {
        val runtime = getOrCreateRuntime(context)

        return GeckoEngine(context, defaultSettings = defaultSettings, runtime = runtime).also {
            WebCompatFeature.install(it)
        }
    }

    fun createClient(context: Context): Client {
        val runtime = getOrCreateRuntime(context)
        return GeckoViewFetchClient(context, runtime)
    }
}
