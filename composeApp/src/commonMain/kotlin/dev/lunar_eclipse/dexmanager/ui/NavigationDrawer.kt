package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Inbox
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material3.NavigationDrawerItem as M3NavigationDrawerItem

@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    navSelected: MutableState<NavSection>,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    @Composable
    fun NavigationDrawerItem(
        labelText: String,
        icon: ImageVector,
        navSection: NavSection
    ) {
        M3NavigationDrawerItem(
            label = { Text(labelText) },
            icon = { Icon(icon, contentDescription = labelText) },
            selected = navSelected.value == navSection,
            onClick = { navSelected.value = navSection; scope.launch { drawerState.close() } }
        )
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Text("Games", Modifier.padding(16.dp), style = MaterialTheme.typography.titleSmall)
                NavigationDrawerItem(
                    labelText = "Living-Form-Dex",
                    icon = Icons.AutoMirrored.Outlined.ListAlt,
                    navSection = NavSection.LivingFormDex
                )
//                HorizontalDivider()
                Text("Cards", Modifier.padding(16.dp), style = MaterialTheme.typography.titleSmall)
                NavigationDrawerItem(
                    labelText = "Sets (Intl)",
                    icon = Icons.Outlined.Folder,
                    navSection = NavSection.SetsIntl
                )
                NavigationDrawerItem(
                    labelText = "Sets (Jp)",
                    icon = Icons.Outlined.Folder,
                    navSection = NavSection.SetsJp
                )
                NavigationDrawerItem(
                    labelText = "Storage",
                    icon = Icons.Outlined.Inbox,
                    navSection = NavSection.Storage
                )
                Text("App", Modifier.padding(16.dp), style = MaterialTheme.typography.titleSmall)
                NavigationDrawerItem(
                    labelText = "Settings",
                    icon = Icons.Outlined.Settings,
                    navSection = NavSection.Settings
                )
            }
        },
        content = content
    )
}