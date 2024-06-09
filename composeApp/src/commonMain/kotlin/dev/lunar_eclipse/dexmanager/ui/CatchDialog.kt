package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.db.UserData
import dev.lunar_eclipse.dexmanager.koinViewModel

@Composable
fun CatchDialog(
    onDismissRequest: () -> Unit
) {
    val viewModel = koinViewModel<DexViewModel>()
    val currentIndex by viewModel.currentIndexState

    if (currentIndex != null) {
        val dex by viewModel.dexState.collectAsState()
        val item = dex[currentIndex!!]

        var checkedCaught by remember { mutableStateOf(true) }
        var checkedOriginalTrainer by remember {
            mutableStateOf(
                item.userData?.originalTrainer ?: true
            )
        }
        var checkedShiny by remember { mutableStateOf(item.userData?.shiny ?: false) }

        AlertDialog(onDismissRequest = { onDismissRequest() },
            title = { Text("Catch") },
            text = {
                Row {
                    Column(modifier = Modifier.weight(1f)) {
//                    if (entry.first) {
//                        CircularProgressIndicator(
//                            modifier = Modifier.width(64.dp),
//                            color = MaterialTheme.colorScheme.secondary,
//                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
//                        )
//                    } else {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { checkedCaught = !checkedCaught }
                                .fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = checkedCaught,
                                onCheckedChange = { checkedCaught = it })
                            Text("Caught")
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable(checkedCaught) {
                                    checkedOriginalTrainer = !checkedOriginalTrainer
                                }
                                .fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = checkedOriginalTrainer,
                                onCheckedChange = { checkedOriginalTrainer = it },
                                enabled = checkedCaught
                            )
                            Text("OT")
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable(checkedCaught) { checkedShiny = !checkedShiny }
                                .fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = checkedShiny,
                                onCheckedChange = { checkedShiny = it },
                                enabled = checkedCaught
                            )
                            Text("Shiny")
                        }
//                    }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        BoxInfo(item)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    if (checkedCaught) {
                        val userData = UserData(
                            item.dexEntry.id,
                            checkedShiny,
                            checkedOriginalTrainer,
                        )

                        viewModel.insertUserData(
                            userData
                        )
                    } else {
                        viewModel.deleteUserData(item.dexEntry.id)
                    }

                    onDismissRequest()
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text("Cancel")
                }
            }
        )
    }
}