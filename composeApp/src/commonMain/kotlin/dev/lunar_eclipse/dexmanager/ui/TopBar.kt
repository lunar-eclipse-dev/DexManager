package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(
    scope: CoroutineScope,
    drawerState: DrawerState,
    darkTheme: Boolean,
    toggleDarkTheme: () -> Unit
) {
    TopAppBar(
        title = {
            Text("DexManager")
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }) {
                Icon(
                    Icons.Filled.Menu,
                    "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = toggleDarkTheme) {
                Icon(
                    if (darkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                    "Change Theme"
                )
            }
        }
    )
}