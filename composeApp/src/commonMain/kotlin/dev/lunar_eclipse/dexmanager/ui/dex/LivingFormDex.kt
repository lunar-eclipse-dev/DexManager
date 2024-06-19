package dev.lunar_eclipse.dexmanager.ui.dex

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ListAlt
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.input.ImeAction
import dev.lunar_eclipse.dexmanager.DexViewModel
import dev.lunar_eclipse.dexmanager.koinViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LivingFormDex(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    var openFilters by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var openCatchDialog by remember { mutableStateOf(false) }
    val viewModel = koinViewModel<DexViewModel>()

    var openSearch by remember { mutableStateOf(false) }
    var searchText by rememberSaveable { mutableStateOf("") }

    var navSelected by remember { mutableStateOf(BottomNavSection.Dex) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (viewModel.search.value != null) {
                            "Search for \"${viewModel.search.value}\""
                        } else {
                            "Living-Form-Dex"
                        }
                    )
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
                    IconButton(onClick = { openFilters = true }) {
                        Icon(Icons.Filled.FilterAlt, "Filter")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.AutoMirrored.Outlined.ListAlt, "Dex") },
                    label = { Text("Dex") },
                    selected = navSelected == BottomNavSection.Dex,
                    onClick = { navSelected = BottomNavSection.Dex }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Outlined.BarChart, "Stats") },
                    label = { Text("Stats") },
                    selected = navSelected == BottomNavSection.Stats,
                    onClick = { navSelected = BottomNavSection.Stats }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { openSearch = true }) {
                Icon(Icons.Filled.Search, "Search")
            }
        }
    ) { contentPadding ->
        val currentEntry by viewModel.currentIndexState

        Column(Modifier.padding(contentPadding)) {
            when (navSelected) {
                BottomNavSection.Dex -> Dex()
                BottomNavSection.Stats -> TODO()
            }
        }

        if (openFilters) {
            ModalBottomSheet(
                onDismissRequest = { openFilters = false },
                sheetState = sheetState
            ) {
                Filters()
            }
        }

        if (openSearch) {
            val requester = remember { FocusRequester() }

            AlertDialog(
                onDismissRequest = { openSearch = false },
                text = {
                    TextField(
                        value = searchText,
                        onValueChange = { searchText = it },
                        label = { Text("Search") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                viewModel.search.value = searchText
                                searchText = ""
                                openSearch = false
                            }
                        ),
                        modifier = Modifier
                            .focusable()
                            .focusRequester(requester),
                    )

                    SideEffect {
                        requester.requestFocus()
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.search.value = searchText
                        searchText = ""
                        openSearch = false
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        viewModel.search.value = null
                        searchText = ""
                        openSearch = false
                    }) {
                        Text("Clear")
                    }
                }
            )
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