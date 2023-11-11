package com.grnt.bababrowser001.components

import android.annotation.SuppressLint
import android.content.Context
import com.grnt.bababrowser001.push.FirebasePush
import mozilla.components.feature.push.AutoPushFeature
import mozilla.components.feature.push.PushConfig
import mozilla.components.lib.crash.CrashReporter
import mozilla.components.support.base.log.logger.Logger

@SuppressLint("DiscouragedApi")
class Push(
    context: Context,
    crashReporter: CrashReporter,
) {
    val feature by lazy {
        pushConfig?.let { config ->
            AutoPushFeature(
                context = context,
                service = pushService,
                config = config,
                crashReporter = crashReporter,
            )
        }
    }

    /**
     * The push configuration data class used to initialize the AutoPushFeature.
     *
     * If we have the `project_id` resource, then we know that the Firebase configuration and API
     * keys are available for the FCM service to be used.
     */
    private val pushConfig by lazy {
        val logger = Logger("AutoPush")

        val resId = context.resources.getIdentifier("project_id", "string", context.packageName)
        if (resId == 0) {
            logger.info("No push keys found. Exiting..")
            return@lazy null
        }
        logger.info("Push keys detected, instantiation beginning..")
        val projectId = context.resources.getString(resId)
        PushConfig(projectId)
    }

    private val pushService by lazy { FirebasePush() }
}