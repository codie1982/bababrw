package com.grnt.bababrowser001

import android.content.Intent
import android.net.Uri
import com.grnt.bababrowser001.ext.components
import mozilla.components.lib.crash.CrashReporter
import mozilla.components.lib.crash.ui.AbstractCrashListActivity

class CrashListActivity : AbstractCrashListActivity() {
    override val crashReporter: CrashReporter by lazy { components.analytics.crashReporter }

    override fun onCrashServiceSelected(url: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
            `package` = packageName
        }
        startActivity(intent)
        finish()
    }
}
