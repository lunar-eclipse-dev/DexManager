package dev.lunar_eclipse.dexmanager

import kotlin.math.absoluteValue

fun Long.ordinal() = toString() + (this.absoluteValue % 100).toInt().ordinalEnding()