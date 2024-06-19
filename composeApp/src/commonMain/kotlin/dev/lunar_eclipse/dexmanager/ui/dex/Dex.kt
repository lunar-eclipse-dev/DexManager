package dev.lunar_eclipse.dexmanager.ui.dex

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.db.DexData
import dev.lunar_eclipse.dexmanager.koinViewModel

@Composable
fun Dex() {
    val viewModel = koinViewModel<DexViewModel>()
    val data by viewModel.dexState.collectAsState()
    val showCaught by viewModel.showCaught
    val showNotCaught by viewModel.showNotCaught
    val search by viewModel.search

    val filteredData = data
        .filterCaught(showCaught, showNotCaught)
        .filter { viewModel.showGens.contains(it.generation.id.toInt()) }
        .filterSearch(search)

    DexContainer(filteredData)
}

private fun List<DexData>.filterCaught(showCaught: Boolean, showNotCaught: Boolean): List<DexData> =
    when (showCaught to showNotCaught) {
        true to true -> this
        true to false -> filter { it.userData != null }
        false to true -> filter { it.userData == null }
        else -> emptyList()
    }

private fun List<DexData>.filterSearch(search: String?) =
    if (search != null) {
        filter { it.dexEntry.name.lowercase().contains(search.lowercase()) }
    } else {
        this
    }