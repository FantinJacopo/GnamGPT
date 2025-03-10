package com.gnamgpt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gnamgpt.data.UsersDatabase
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    // LiveData per tenere traccia del tema (scuro o chiaro)
    private var _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: MutableLiveData<Boolean> get() = _isDarkMode // Rendi pubblico solo il getter

    private val usersDatabase = UsersDatabase()

    init {
        // Recupera le preferenze dell'utente quando il ViewModel viene inizializzato
        fetchUserPreferences()
    }

    private fun fetchUserPreferences() {
        viewModelScope.launch {
            // Recupera le preferenze dell'utente dal database
            usersDatabase.getUserPreferences { theme, _ ->
                // Imposta il tema in base al valore recuperato
                _isDarkMode.value = theme == "dark"
            }

            // Se il valore del tema è ancora null, imposta un tema predefinito (chiaro)
            if (_isDarkMode.value == null) {
                _isDarkMode.value = false
            }
        }
    }

    fun updateTheme(isDarkModeEnabled: Boolean) {
        viewModelScope.launch {
            // Salva le nuove preferenze nel database
            usersDatabase.saveUserPreferences(isDarkModeEnabled, "en") //TODO: implementare il cambio lingua

            // Aggiorna il valore del tema
            _isDarkMode.value = isDarkModeEnabled
        }
    }
}