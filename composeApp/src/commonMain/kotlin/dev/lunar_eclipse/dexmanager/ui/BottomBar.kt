package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomBar(navSelected: Int, selectNav: (Int) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Outlined.ListAlt, "Dex") },
            label = { Text("Dex") },
            selected = navSelected == 0,
            onClick = { selectNav(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.BarChart, "Stats") },
            label = { Text("Stats") },
            selected = navSelected == 1,
            onClick = { selectNav(1) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Outlined.Settings, "Settings") },
            label = { Text("Settings") },
            selected = navSelected == 2,
            onClick = { selectNav(2) }
        )
    }
}