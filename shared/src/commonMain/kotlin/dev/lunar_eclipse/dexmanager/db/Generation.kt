package dev.lunar_eclipse.dexmanager.db

data class Generation(
    val id: Long,
    val dexFirst: Long,
    val dexLast: Long,
    val startingBox: Long
)
