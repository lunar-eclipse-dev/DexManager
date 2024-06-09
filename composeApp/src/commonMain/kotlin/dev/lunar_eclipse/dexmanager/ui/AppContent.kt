package dev.lunar_eclipse.dexmanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.koinViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppContent() {
    val viewModel = koinViewModel<DexViewModel>()

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var openCatchDialog by remember { mutableStateOf(false) }

    var darkTheme by remember { mutableStateOf(false) }

    var navSelected by remember { mutableStateOf(0) }

    AppTheme(darkTheme, dynamicColor = false) {
        DexFilterDrawer(drawerState = drawerState) {
            Scaffold(
                topBar = { TopBar(scope, drawerState, darkTheme) { darkTheme = !darkTheme } },
                bottomBar = { BottomBar(navSelected) { navSelected = it } }
            ) { contentPadding ->
                val currentEntry by viewModel.currentIndexState

                Column(Modifier.padding(contentPadding)) {
                    Dex()
                }

                if (currentEntry != null) {
                    ModalBottomSheet(
                        onDismissRequest = { viewModel.currentIndexState.value = null },
                        sheetState = sheetState
                    ) {
                        DexDetails(openCollect = { openCatchDialog = true })
                    }

                    if (openCatchDialog) {
                        CatchDialog(onDismissRequest = { openCatchDialog = false })
                    }
                }
            }
        }
    }
}