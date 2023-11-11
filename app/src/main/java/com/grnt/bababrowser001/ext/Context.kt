package com.grnt.bababrowser001.ext

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.EXTRA_TEXT
import androidx.annotation.StringRes
import androidx.core.app.NotificationManagerCompat
import com.grnt.bababrowser001.BrowserApplication
import com.grnt.bababrowser001.Components
import com.grnt.bababrowser001.R
import mozilla.components.support.base.android.NotificationsDelegate
import mozilla.components.support.base.log.Log


val Context.components: Components
    get() = application.components
val Context.application: BrowserApplication
    get() = applicationContext as BrowserApplication


fun Context.getPreferenceKey(@StringRes resourceId: Int): String =
    resources.getString(resourceId)

/**
 *  Shares content via [ACTION_SEND] intent.
 *
 * @param text the data to be shared  [EXTRA_TEXT]
 * @param subject of the intent [EXTRA_TEXT]
 * @return true it is able to share false otherwise.
 */
fun Context.share(text: String, subject: String = ""): Boolean {
    return try {
        val intent = Intent(ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(EXTRA_TEXT, text)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val shareIntent = Intent.createChooser(intent, getString(R.string.menu_share_with)).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(shareIntent)
        true
    } catch (e: ActivityNotFoundException) {
        Log.log(Log.Priority.WARN, message = "No activity to share to found", throwable = e, tag = "Reference-Browser")
        false
    }
}