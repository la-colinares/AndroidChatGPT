package com.lacolinares.androidchatgpt.data.mapper

import com.lacolinares.androidchatgpt.data.model.MessageResponse
import com.lacolinares.androidchatgpt.domain.model.ChatMessage
import com.lacolinares.androidchatgpt.utils.MessageType

fun MessageResponse.toMessage(): ChatMessage{
    val message = this.choices.map(MessageResponse.Choices::text).joinToString("\n\n").trimStart(',', '\n', ' ')
    return ChatMessage(
        messageType = MessageType.AI,
        message = message
    )
}