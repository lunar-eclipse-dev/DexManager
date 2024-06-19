package dev.lunar_eclipse.dexmanager.ui.dex

import androidx.compose.runtime.Composable
import dev.lunar_eclipse.dexmanager.db.DexData

@Composable
expect fun DexContainer(data: List<DexData>)