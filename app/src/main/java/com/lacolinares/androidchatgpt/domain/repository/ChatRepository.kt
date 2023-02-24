package com.lacolinares.androidchatgpt.domain.repository

import com.lacolinares.androidchatgpt.domain.model.ChatMessage
import kotlinx.coroutines.flow.Flow

interface ChatRepository {
    suspend fun sendMessage(message: String)
    suspend fun saveMessage(chatMessage: ChatMessage, onSuccess: (() -> Unit)? = null)
    val messages: Flow<List<ChatMessage>>
}