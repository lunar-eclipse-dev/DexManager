package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.db.DexData

@Composable
actual fun DexContainer(data: List<DexData>) {
    Box(Modifier.fillMaxSize()) {
        val listState = rememberLazyListState()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 8.dp),
            state = listState
        ) {
            items(
                items = data,
                key = { it.dexEntry.id }
            ) { item ->
                DexItem(item)
            }
        }

        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(listState)
        )
    }
}