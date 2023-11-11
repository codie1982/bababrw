package com.grnt.bababrowser001.tabs

import android.content.Context
import androidx.annotation.RawRes
import com.grnt.bababrowser001.R

object PrivatePage {
    /**
     * Load and generate a private browsing page for the given url and html/css resources
     */
    fun createPrivateBrowsingPage(
        context: Context,
        url: String,
        @RawRes htmlRes: Int = R.raw.private_mode,
        @RawRes cssRes: Int = R.raw.private_style,
    ): String {
        val css = context.resources.openRawResource(cssRes).bufferedReader().use {
            it.readText()
        }

        return context.resources.openRawResource(htmlRes)
            .bufferedReader()
            .use { it.readText() }
            .replace("%pageTitle%", context.getString(R.string.private_browsing_title))
            .replace("%pageBody%", context.getString(R.string.private_browsing_body))
            .replace("%privateBrowsingSupportUrl%", url)
            .replace("%css%", css)
    }
}