package dev.lunar_eclipse.dexmanager.db

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.use
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dev.lunar_eclipse.dexmanager.resources.Res
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import java.io.FileOutputStream

private const val DB_NAME: String = "dexdata.db"

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            AppDatabase.Schema,
            context,
            DB_NAME,
            callback = object : AndroidSqliteDriver.Callback(AppDatabase.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            })
    }
}
