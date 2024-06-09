import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.seiko.imageloader.LocalImageLoader
import dev.lunar_eclipse.dexmanager.db.getDatabaseBuilder
import dev.lunar_eclipse.dexmanager.ui.generateImageLoader

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "DexManager",
    ) {

        CompositionLocalProvider(
            LocalImageLoader provides remember { generateImageLoader() }
        ) {
            App(getDatabaseBuilder())
        }
    }
}