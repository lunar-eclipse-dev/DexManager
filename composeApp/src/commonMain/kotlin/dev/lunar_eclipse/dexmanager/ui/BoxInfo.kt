package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lunar_eclipse.dexmanager.db.DexData
import dev.lunar_eclipse.dexmanager.ordinal

@Composable
fun BoxInfo(item: DexData) {
    val numberInGeneration = item.dexEntry.id - item.generation.dexFirst

    val box = item.generation.startingBox + (numberInGeneration / 30)

    val numberInBox = (numberInGeneration % 30).toInt()
    val boxRow = numberInBox / 6
    val boxColumn = numberInBox % 6

    Text("${box.ordinal()} box")
    Text("${(boxRow + 1).ordinal()} row")
    Text("${(boxColumn + 1).ordinal()} column")
    BoxPositionIndicator(boxRow, boxColumn, modifier = Modifier.padding(top = 8.dp))
}