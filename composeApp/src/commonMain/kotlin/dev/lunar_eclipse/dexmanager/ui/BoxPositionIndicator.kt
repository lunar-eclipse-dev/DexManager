package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

private const val STROKE_WIDTH: Float = 4f

@Composable
fun BoxPositionIndicator(row: Int, column: Int, modifier: Modifier = Modifier) {
//    val boxColor = Color(0xFFE4000F)
    val boxColor = MaterialTheme.colorScheme.primary

    Spacer(
        modifier = modifier
            .aspectRatio(6f / 5f)
            .drawBehind {
                val slotSize = size.width / 6f

                drawRect(
                    color = boxColor,
                    style = Stroke(STROKE_WIDTH)
                )

                for (i in 1..5) {
                    drawLine(
                        color = boxColor,
                        start = Offset(i * slotSize, 0f),
                        end = Offset(i * slotSize, size.height),
                        strokeWidth = STROKE_WIDTH
                    )
                }

                for (i in 1..4) {
                    drawLine(
                        color = boxColor,
                        start = Offset(0f, i * slotSize),
                        end = Offset(size.width, i * slotSize),
                        strokeWidth = STROKE_WIDTH
                    )
                }

                drawRect(
                    color = boxColor,
                    topLeft = Offset(column * slotSize, row * slotSize),
                    size = Size(slotSize, slotSize)
                )
            }
    )
}