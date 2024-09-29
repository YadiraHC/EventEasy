import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("eventeasy_prefs", Context.MODE_PRIVATE)

    var isAuthenticated by mutableStateOf(false)
        private set

    // Simulación de inicio de sesión
    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            // Simular llamada de autenticación
            delay(2000)
            isAuthenticated = true
            // Guardar estado en SharedPreferences
            sharedPreferences.edit().putBoolean("isAuthenticated", true).apply()
        }
    }

    // Cerrar sesión
    fun signOut() {
        isAuthenticated = false
        sharedPreferences.edit().putBoolean("isAuthenticated", false).apply()
    }

    // Verificar si el usuario ya está autenticado
    fun checkIfAuthenticated() {
        isAuthenticated = sharedPreferences.getBoolean("isAuthenticated", false)
    }
}
