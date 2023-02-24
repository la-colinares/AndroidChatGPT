package com.lacolinares.androidchatgpt.domain.usecases

import com.lacolinares.androidchatgpt.domain.repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chatRepository: ChatRepository
) {

    suspend operator fun invoke(message: String) {
        chatRepository.sendMessage(message)
    }
}