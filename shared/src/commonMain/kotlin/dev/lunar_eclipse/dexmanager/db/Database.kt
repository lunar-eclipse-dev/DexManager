package dev.lunar_eclipse.dexmanager.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class Database(driverFactory: DriverFactory) {
    private val database = AppDatabase(driverFactory.createDriver())
    private val dexQuery = database.dexQueries
    private val userDataQuery = database.user_dataQueries

    fun getAllDataAsFlow(): Flow<List<DexData>> {
        return dexQuery.selectAllWithData(::mapDexEntryWithData).asFlow().mapToList(Dispatchers.IO)
    }

    fun insertUserData(data: UserData) {
        userDataQuery.insert(data.userDataId, data.shiny, data.originalTrainer)
    }

    fun deleteUserData(id: Long) {
        userDataQuery.delete(id)
    }

    fun deleteUserData(data: UserData) {
        deleteUserData(data.userDataId)
    }

    private fun mapDexEntryWithData(
        dexId: Long,
        name: String,
        formName: String?,
        formId: String?,
        genderId: String?,
        keyword: String,
        generation: Long,
        type1: String,
        type2: String?,
        national: Long,
        swSh: String?,
        bdSp: String?,
        la: String?,
        scVi: String?,
        genId: Long,
        dexFirst: Long,
        dexLast: Long,
        startingBox: Long,
        userDataId: Long?,
        shiny: Boolean?,
        originalTrainer: Boolean?,
    ) = DexData(
        DexEntry(
            dexId,
            name,
            formName,
            formId,
            genderId,
            keyword,
            generation,
            type1,
            type2,
            national,
            swSh,
            bdSp,
            la,
            scVi
        ),
        Generation(genId, dexFirst, dexLast, startingBox),
        userDataId?.let {
            UserData(
                userDataId,
                shiny ?: false,
                originalTrainer ?: false
            )
        }
    )
}