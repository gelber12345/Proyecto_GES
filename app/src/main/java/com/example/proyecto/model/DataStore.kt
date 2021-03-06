package com.example.proyecto.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Data(private val context: Context) {

    // to make sure there's only one instance
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("datos")

        val USER_PLACA_KEY = stringPreferencesKey("user_placa")
        val USER_SOAT_KEY = stringPreferencesKey("user_soat")
        val USER_DATE_KEY = stringPreferencesKey("user_date")
        val USER_SESION_KEY = stringPreferencesKey("user_sesion")
        val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        val USER_PASSWORD_KEY = stringPreferencesKey("user_password")
        val USER_NAME_KEY = stringPreferencesKey("user_name")

    }


    //get the PLACA Size
    val getPlaca: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_PLACA_KEY] ?: "7SD AS2"
        }

    //save PLACA into datastore
    suspend fun savePlaca(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PLACA_KEY] = data
        }
    }

    //get the SOAT Size
    val getSoat: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_SOAT_KEY] ?: "SOAT"
        }

    //save SOAT into datastore
    suspend fun saveSoat(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_SOAT_KEY] = data
        }
    }
    //get the DATE Size
    val getDate: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_DATE_KEY] ?: "12/10/21"
        }

    //save DATE into datastore
    suspend fun saveDate(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_DATE_KEY] = data
        }
    }
    //get the EMAIL Size
    val getEmail: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_EMAIL_KEY] ?: "example@hotmail.com"
        }

    //save EMAIL into datastore
    suspend fun saveEmail(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = data
        }
    }

    //get the PASSWORD Size
    val getPassword: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_PASSWORD_KEY] ?: "123"
        }

    //save PASSWORD into datastore
    suspend fun savePassword(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_PASSWORD_KEY] = data
        }
    }
    //get the PASSWORD Size
    val getName: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_NAME_KEY] ?: "Anonimo"
        }

    //save PASSWORD into datastore
    suspend fun saveName(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = data
        }
    }

    //get the SESION Size
    val getSesion: Flow<String> = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USER_SESION_KEY] ?: "example@hotmail.com"
        }

    //save SESION into datastore
    suspend fun saveSesion(data: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_SESION_KEY] = data
        }
    }
}