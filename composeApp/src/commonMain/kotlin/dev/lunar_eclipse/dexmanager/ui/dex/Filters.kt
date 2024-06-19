package dev.lunar_eclipse.dexmanager.ui.dex

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.koinViewModel
import dev.lunar_eclipse.dexmanager.ui.NavDrawerCheckbox

@Composable
fun Filters() {
    val viewModel = koinViewModel<DexViewModel>()
    var showCaught by viewModel.showCaught
    var showNotCaught by viewModel.showNotCaught
    val showGens = viewModel.showGens

    val scrollState = rememberScrollState()

    Column(modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 64.dp)) {
        NavDrawerCheckbox("Show Caught", showCaught) { showCaught = !showCaught }
        NavDrawerCheckbox("Show Not-Caught", showNotCaught) { showNotCaught = !showNotCaught }
        Text("Generations")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
        ) {
            for (i in 1..9) {
                Box(
                    Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .clickable {
                            if (showGens.contains(i)) {
                                showGens.remove(i)
                            } else {
                                showGens.add(i)
                            }
                        }
                        .background(
                            if (showGens.contains(i)) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.surface
                            },
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        i.toString(), color = if (showGens.contains(i)) {
                            MaterialTheme.colorScheme.onPrimaryContainer
                        } else {
                            MaterialTheme.colorScheme.onSurface
                        },
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}