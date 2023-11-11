package com.grnt.bababrowser001.ext

import com.grnt.bababrowser001.BuildConfig

val isCrashReportActive: Boolean
    get() = !BuildConfig.DEBUG && BuildConfig.CRASH_REPORTING_ENABLED