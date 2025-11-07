package mx.edu.utez.datastore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utez.datastore.ui.screens.SettingsScreen
import mx.edu.utez.datastore.ui.theme.DataStoreTheme
import mx.edu.utez.datastore.viewmodel.SettingsViewModel

class MainActivity : ComponentActivity() {

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataStoreTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    SettingsApp(settingsViewModel)
                }
            }
        }
    }
}

@Composable
fun SettingsApp(settingsViewModel: SettingsViewModel = viewModel()) {
    SettingsScreen(settingsViewModel)
}
