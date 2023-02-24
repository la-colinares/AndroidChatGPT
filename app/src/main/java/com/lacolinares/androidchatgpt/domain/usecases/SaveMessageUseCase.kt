package com.lacolinares.androidchatgpt.domain.usecases

import com.lacolinares.androidchatgpt.domain.model.ChatMessage
import com.lacolinares.androidchatgpt.domain.repository.ChatRepository
import javax.inject.Inject

class SaveMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {
    suspend operator fun invoke(chatMessage: ChatMessage, onSuccess: (() -> Unit)? = null) {
        chatRepository.saveMessage(
            chatMessage = chatMessage,
            onSuccess = { onSuccess?.invoke() }
        )
    }
}