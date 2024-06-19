package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.lunar_eclipse.dexmanager.ui.dex.LivingFormDex

@Composable
fun AppContent() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val darkTheme by remember { mutableStateOf(false) }

    val navSelected = remember { mutableStateOf(NavSection.LivingFormDex) }

    AppTheme(darkTheme, dynamicColor = false) {
        NavigationDrawer(drawerState = drawerState, navSelected = navSelected) {
            when (navSelected.value) {
                NavSection.LivingFormDex -> LivingFormDex(drawerState)
                NavSection.SetsIntl -> TODO()
                NavSection.SetsJp -> TODO()
                NavSection.Storage -> TODO()
                NavSection.Settings -> TODO()
            }
        }
    }
}