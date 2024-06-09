package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.DrawerState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.koinViewModel

private val genNames = arrayOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DexFilterDrawer(drawerState: DrawerState, content: @Composable () -> Unit) {
    val viewModel = koinViewModel<DexViewModel>()
    var showCaught by viewModel.showCaught
    var showNotCaught by viewModel.showNotCaught
    val showGens = viewModel.showGens

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Text("Filter", Modifier.padding(16.dp))
                NavDrawerCheckbox("Show Caught", showCaught) { showCaught = !showCaught }
                NavDrawerCheckbox("Show Not-Caught", showNotCaught) { showNotCaught = !showNotCaught }
                Text("Generations", Modifier.padding(16.dp))
                FlowRow(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (i in 1..9) {
                        FilterChip(
                            selected = showGens.contains(i),
                            label = { Text(genNames[i - 1]) },
                            onClick = {
                                if (showGens.contains(i)) showGens.remove(i) else showGens.add(i)
                            },
                            leadingIcon = if (showGens.contains(i)) {
                                {
                                    Icon(
                                        Icons.Filled.Done,
                                        contentDescription = "Checked",
                                        modifier = Modifier.size(FilterChipDefaults.IconSize)
                                    )
                                }
                            } else null
                        )
                    }
                }
            }
        },
        content = content
    )
}