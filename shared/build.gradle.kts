import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("dev.lunar_eclipse.dexmanager.db")
        }
    }
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.components.resources)
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.androidx.lifecycle.viewmodel.compose)
            implementation(libs.sqlite.bundled)
            implementation(libs.sqldelight.extensions.coroutines)
            implementation(libs.koin.core)
            implementation(libs.kodein.di.compose)
        }
    }
}

android {
    namespace = "dev.lunar_eclipse.dexmanager.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    dependencies {
        implementation(libs.androidx.room.runtime)
        implementation(libs.sqldelight.driver.android)
        implementation(libs.koin.androidx.compose)
    }
}

dependencies {
    ksp(libs.androidx.room.compiler)
}

compose.resources {
    publicResClass = true
    packageOfResClass = "dev.lunar_eclipse.dexmanager.resources"
    generateResClass
}