package mx.edu.utez.datastore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import mx.edu.utez.datastore.data.SettingsDataStore
import mx.edu.utez.datastore.data.SettingsUiState


class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = SettingsDataStore(application)

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState

    init {
        // Combina los flujos y observa los cambios de DataStore
        viewModelScope.launch {
            combine(
                dataStore.isDarkMode,
                dataStore.brightness,
                dataStore.volume,
                dataStore.difficulty
            ) { darkMode, brightness, volume, difficulty ->
                SettingsUiState(
                    isDarkMode = darkMode,
                    brightness = brightness,
                    volume = volume,
                    difficulty = difficulty
                )
            }.collect {
                _uiState.value = it
            }
        }
    }

    fun setDarkMode(value: Boolean) {
        viewModelScope.launch {
            dataStore.setDarkMode(value)
        }
    }

    fun setBrightness(value: Float) {
        viewModelScope.launch {
            dataStore.setBrightness(value)
        }
    }

    fun setVolume(value: Float) {
        viewModelScope.launch {
            dataStore.setVolume(value)
        }
    }

    fun setDifficulty(value: String) {
        viewModelScope.launch {
            dataStore.setDifficulty(value)
        }
    }
}
