package mx.edu.utez.datastore.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


private val Context.dataStore by preferencesDataStore(name = "settings_preferences")

class SettingsDataStore(private val context: Context) {


    companion object {
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        val BRIGHTNESS_KEY = floatPreferencesKey("brightness")
        val VOLUME_KEY = floatPreferencesKey("volume")
        val DIFFICULTY_KEY = stringPreferencesKey("difficulty")
    }


    val isDarkMode: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[DARK_MODE_KEY] ?: false
    }

    val brightness: Flow<Float> = context.dataStore.data.map { prefs ->
        prefs[BRIGHTNESS_KEY] ?: 0.5f
    }

    val volume: Flow<Float> = context.dataStore.data.map { prefs ->
        prefs[VOLUME_KEY] ?: 0.5f
    }

    val difficulty: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[DIFFICULTY_KEY] ?: "Normal"
    }


    suspend fun setDarkMode(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_MODE_KEY] = value
        }
    }

    suspend fun setBrightness(value: Float) {
        context.dataStore.edit { prefs ->
            prefs[BRIGHTNESS_KEY] = value
        }
    }

    suspend fun setVolume(value: Float) {
        context.dataStore.edit { prefs ->
            prefs[VOLUME_KEY] = value
        }
    }

    suspend fun setDifficulty(value: String) {
        context.dataStore.edit { prefs ->
            prefs[DIFFICULTY_KEY] = value
        }
    }
}