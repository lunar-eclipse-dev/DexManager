package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flare
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.CheckBox
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.ui.AutoSizeImage
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.db.DexData
import dev.lunar_eclipse.dexmanager.db.UserData
import dev.lunar_eclipse.dexmanager.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DexItem(item: DexData) {
    val viewModel = koinViewModel<DexViewModel>()

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            if (it == SwipeToDismissBoxValue.StartToEnd || it == SwipeToDismissBoxValue.EndToStart) {
                if (viewModel.dexState.value[item.dexEntry.id.toInt() - 1].userData == null) {
                    viewModel.insertUserData(
                        UserData(
                            item.dexEntry.id,
                            shiny = false,
                            originalTrainer = true
                        )
                    )
                } else {
                    viewModel.deleteUserData(item.dexEntry.id)
                }
            }

            false
        },
        positionalThreshold = { distance -> distance * 0.3f }
    )

    SwipeToDismissBox(
        state = dismissState,
        modifier = Modifier,
        backgroundContent = {
            DismissBackground(item.userData != null)
        },
        content = {
            ListItem(
                headlineContent = {
                    Text(item.dexEntry.name)
                },
                overlineContent = {
                    Text("#${item.dexEntry.national}")
                },
                supportingContent = item.dexEntry.formGender.let {
                    if (it.isNotBlank()) {
                        { Text(it) }
                    } else null
                },
                leadingContent = {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .background(
                                MaterialTheme.colorScheme.surfaceBright,
                                MaterialTheme.shapes.medium
                            )
                    ) {
                        AutoSizeImage(
                            item.dexEntry.getSpriteUrl(item.userData?.shiny == true),
                            contentDescription = "sprite",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                },
                trailingContent = {
                    if (item.userData != null) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (!item.userData!!.originalTrainer) {
                                InfoChip(
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Person,
                                            contentDescription = "original trainer"
                                        )
                                    },
                                    label = { Text("Non-OT") }
                                )
                            }
                            if (item.userData!!.shiny) {
                                InfoChip(
                                    leadingIcon = {
                                        Icon(
                                            Icons.Filled.Flare,
                                            contentDescription = "shiny"
                                        )
                                    },
                                    label = { Text("Shiny") },
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                },
                colors = ListItemDefaults.colors(
                    containerColor = if (item.userData != null) {
//                        Color(220, 240, 220)
                        MaterialTheme.colorScheme.surfaceVariant
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                ),
                modifier = Modifier.clickable {
                    viewModel.currentIndexState.value = item.dexEntry.id.toInt() - 1
                }
            )
        }
    )

    HorizontalDivider()
}

@Composable
private fun DismissBackground(caught: Boolean) {
    val color = if (caught) {
        MaterialTheme.colorScheme.error
    } else {
        MaterialTheme.colorScheme.primary
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        if (caught) {
            Icon(
                Icons.Outlined.Delete,
                tint = MaterialTheme.colorScheme.onError,
                contentDescription = "Release"
            )
        } else {
            Icon(
                Icons.Outlined.CheckBox,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "Catch"
            )
        }

        Spacer(modifier = Modifier)

        if (caught) {
            Icon(
                Icons.Outlined.Delete,
                tint = MaterialTheme.colorScheme.onError,
                contentDescription = "Release"
            )
        } else {
            Icon(
                Icons.Outlined.CheckBox,
                tint = MaterialTheme.colorScheme.onPrimary,
                contentDescription = "Catch"
            )
        }
    }
}