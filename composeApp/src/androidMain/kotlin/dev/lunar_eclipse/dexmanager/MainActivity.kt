package dev.lunar_eclipse.dexmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.cache.memory.maxSizePercent
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.intercept.bitmapMemoryCacheConfig
import com.seiko.imageloader.intercept.imageMemoryCacheConfig
import com.seiko.imageloader.intercept.painterMemoryCacheConfig
import com.seiko.imageloader.option.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CompositionLocalProvider(
                LocalImageLoader provides remember { generateImageLoader() }
            ) {
                App()
            }
        }
    }

    private fun generateImageLoader() = ImageLoader {
        options {
            androidContext(applicationContext)
        }

        components {
            setupDefaultComponents()
        }

        interceptor {
            bitmapMemoryCacheConfig {
                maxSizePercent(applicationContext, 0.25)
            }

            imageMemoryCacheConfig {
                maxSize(50)
            }

            painterMemoryCacheConfig {
                maxSize(50)
            }

            diskCacheConfig {
                maxSizeBytes(512L * 1024L * 1024L)
            }
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}