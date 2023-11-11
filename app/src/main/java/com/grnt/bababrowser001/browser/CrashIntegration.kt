package com.grnt.bababrowser001.browser

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.grnt.bababrowser001.BrowserApplication.Companion.NON_FATAL_CRASH_BROADCAST
import com.grnt.bababrowser001.ext.isCrashReportActive
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mozilla.components.lib.crash.Crash
import mozilla.components.lib.crash.CrashReporter

class CrashIntegration(
    private val context: Context,
    private val crashReporter: CrashReporter,
    private val onCrash: (Crash) -> Unit,
) : DefaultLifecycleObserver {

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (!Crash.isCrashIntent(intent)) {
                return
            }

            val crash = Crash.fromIntent(intent)
            onCrash(crash)
        }
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        if (isCrashReportActive) {
            context.registerReceiver(receiver, IntentFilter(NON_FATAL_CRASH_BROADCAST))
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        if (isCrashReportActive) {
            context.unregisterReceiver(receiver)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun sendCrashReport(crash: Crash) {
        GlobalScope.launch {
            crashReporter.submitReport(crash)
        }
    }
}