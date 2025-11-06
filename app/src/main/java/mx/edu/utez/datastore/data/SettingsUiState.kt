package mx.edu.utez.datastore.data

data class SettingsUiState(
        val isDarkMode: Boolean = false,
        val brightness: Float = 0.5f,
        val volume: Float = 0.5f,
        val difficulty: String = "Normal"
)
