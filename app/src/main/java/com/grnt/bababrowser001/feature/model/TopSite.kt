package com.grnt.bababrowser001.feature.model

sealed class TopSite{
    abstract val id: Long?
    abstract val title: String?
    abstract val url: String
    abstract val createdAt: Long?
    data class Default(
        override val id: Long?,
        override val title: String?,
        override val url: String,
        override val createdAt: Long?,
    ) : TopSite()
}
