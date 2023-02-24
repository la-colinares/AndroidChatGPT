package com.lacolinares.androidchatgpt.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

class ChatDataStore @Inject constructor(private val context: Context) {

    suspend fun setMessage(onSuccess: () -> Unit){
        context.dataStore.edit { prefs ->

        }
    }

    companion object {
        private const val DATA_STORE_NAME = "chat_prefs"
        private val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)

        private val KEY_MESSAGES = stringPreferencesKey("message")
    }
}