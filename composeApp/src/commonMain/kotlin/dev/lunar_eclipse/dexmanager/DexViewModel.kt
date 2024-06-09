package dev.lunar_eclipse.dexmanager

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import dev.lunar_eclipse.dexmanager.db.Database
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.lunar_eclipse.dexmanager.db.DexData
import dev.lunar_eclipse.dexmanager.db.UserData
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DexViewModel(private val db: Database) : ViewModel() {
    private val _dexState: MutableStateFlow<List<DexData>> = MutableStateFlow(emptyList())
    val dexState: StateFlow<List<DexData>> = _dexState.asStateFlow()

    val currentIndexState: MutableState<Int?> = mutableStateOf(null)
    val showCaught = mutableStateOf(true)
    val showNotCaught = mutableStateOf(true)
    val showGens = mutableStateListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    fun loadDex() {
        viewModelScope.launch {
            db.getAllDataAsFlow().collect {
                _dexState.emit(it)
            }
        }
    }

    fun insertUserData(data: UserData) {
        db.insertUserData(data)
    }

    fun deleteUserData(data: UserData) {
        db.deleteUserData(data)
    }

    fun deleteUserData(id: Long) {
        db.deleteUserData(id)
    }

    init {
        loadDex()
    }
}