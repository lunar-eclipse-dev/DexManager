package dev.lunar_eclipse.dexmanager

import androidx.compose.runtime.Composable
import dev.lunar_eclipse.dexmanager.ui.AppContent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        AppContent()
    }
}