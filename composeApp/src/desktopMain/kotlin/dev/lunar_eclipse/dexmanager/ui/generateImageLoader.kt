package dev.lunar_eclipse.dexmanager.ui

import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.intercept.bitmapMemoryCacheConfig
import com.seiko.imageloader.intercept.imageMemoryCacheConfig
import com.seiko.imageloader.intercept.painterMemoryCacheConfig

fun generateImageLoader() = ImageLoader {
    components {
        setupDefaultComponents()
    }

    interceptor {
        bitmapMemoryCacheConfig {
            maxSize(32 * 1024 * 1024)
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