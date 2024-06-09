package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Surface
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InfoChip(
    leadingIcon: @Composable () -> Unit,
    leadingIconColor: Color = MaterialTheme.colorScheme.primary,
    label: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .defaultMinSize(minHeight = 32.dp),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        color = Color.Transparent,
    ) {
        CompositionLocalProvider(
            LocalTextStyle provides MaterialTheme.typography.labelLarge,
            LocalContentColor provides MaterialTheme.colorScheme.onSurface
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(18.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides leadingIconColor,
                        content = leadingIcon
                    )
                }
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    label()
                }
            }
        }
    }
}