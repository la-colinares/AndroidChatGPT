package com.lacolinares.androidchatgpt.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.lacolinares.androidchatgpt.data.mapper.toMessage
import com.lacolinares.androidchatgpt.data.model.MessageRequest
import com.lacolinares.androidchatgpt.data.remote.ChatServiceApi
import com.lacolinares.androidchatgpt.domain.model.ChatMessage
import com.lacolinares.androidchatgpt.domain.repository.ChatRepository
import com.lacolinares.androidchatgpt.utils.AppConstants
import com.lacolinares.androidchatgpt.utils.AppJson
import com.lacolinares.androidchatgpt.utils.MessageType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val context: Context,
    private val api: ChatServiceApi
) : ChatRepository {

    override suspend fun sendMessage(message: String) {
        val request = MessageRequest(
            model = AppConstants.MODEL,
            prompt = message,
            temperature = AppConstants.TEMPERATURE,
            maxTokens = AppConstants.MAX_TOKENS,
            topP = AppConstants.TOP_P,
            frequencyPenalty = AppConstants.FREQUENCY_PENALTY,
            presencePenalty = AppConstants.PRESENCE_PENALTY
        )

        val chatMessageResponse = try {
            api.sendMessage(
                authorization = AppConstants.AUTHORIZATION,
                contentType = AppConstants.APPLICATION_TYPE,
                body = request
            ).toMessage()
        } catch (e: Exception) {
            ChatMessage(
                messageType = MessageType.AI,
                message = AppConstants.DEFAULT_AI_MESSAGE,
                dateTime = System.currentTimeMillis()
            )
        }

        saveMessage(chatMessage = chatMessageResponse)
    }

    override suspend fun saveMessage(chatMessage: ChatMessage, onSuccess: (() -> Unit)?) {
        context.dataStore.edit { prefs ->
            val chatMessages = prefs[KEY_MESSAGES]?.let { AppJson.Json.decodeFromString<List<ChatMessage>>(it) } ?: emptyList()
            val mutableMessages = chatMessages.toMutableList()
            mutableMessages.add(chatMessage)
            prefs[KEY_MESSAGES] = AppJson.Json.encodeToString(mutableMessages)
            onSuccess?.invoke()
        }
    }

    override val messages: Flow<List<ChatMessage>>
        get() = context.dataStore.data.map { prefs ->
            val messages = prefs[KEY_MESSAGES]
            if (messages != null) {
                AppJson.Json.decodeFromString(messages)
            } else {
                emptyList()
            }
        }

    companion object {
        private const val DATA_STORE_NAME = "chat_prefs"
        private val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)

        private val KEY_MESSAGES = stringPreferencesKey("message")
    }
}