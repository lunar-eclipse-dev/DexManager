package dev.lunar_eclipse.dexmanager

import kotlin.math.absoluteValue

fun Int.ordinal() = toString() + ordinalEnding()

fun Int.ordinalEnding(): String = if (absoluteValue % 100 in 11..13) "th" else when (absoluteValue % 10) {
    1 -> "st"
    2 -> "nd"
    3 -> "rd"
    else -> "th"
}