package com.lacolinares.androidchatgpt.domain.model

import com.lacolinares.androidchatgpt.utils.AppConstants
import com.lacolinares.androidchatgpt.utils.MessageType
import kotlinx.serialization.Serializable

@Serializable
data class ChatMessage(
    val id: String = AppConstants.IDGenerator.getUniqueKey(),
    val messageType: MessageType = MessageType.USER,
    val message: String = "",
    val dateTime: Long = System.currentTimeMillis()
)
