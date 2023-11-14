package com.grnt.bababrowser001.feature.model

import android.graphics.drawable.Drawable

sealed class TopSite{
    abstract val id: Long?
    abstract val title: String?
    abstract val url: String
    abstract val icon: Int
    abstract val createdAt: Long?
    data class Default(
        override val id: Long?,
        override val title: String?,
        override val url: String,
        override val icon: Int,
        override val createdAt: Long?,
    ) : TopSite()
}
