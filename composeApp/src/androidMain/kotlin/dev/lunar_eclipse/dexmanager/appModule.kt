package dev.lunar_eclipse.dexmanager

import dev.lunar_eclipse.dexmanager.db.Database
import dev.lunar_eclipse.dexmanager.db.DriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { Database(DriverFactory(androidContext())) }
    viewModelOf(::DexViewModel)
}