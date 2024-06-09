package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.db.DexData

@Composable
actual fun DexContainer(data: List<DexData>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            items = data,
            key = { it.dexEntry.id }
        ) { item ->
            DexItem(item)
        }
    }
}