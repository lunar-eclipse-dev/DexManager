package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.runtime.Composable
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.db.DexData
import org.koin.compose.koinInject

@Composable
expect fun DexContainer(data: List<DexData>)