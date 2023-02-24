package com.lacolinares.androidchatgpt.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageRequest(
    val model: String,
    val prompt: String,
    val temperature: Int,
    @SerialName("max_tokens")
    val maxTokens: Int,
    @SerialName("top_p")
    val topP: Int,
    @SerialName("frequency_penalty")
    val frequencyPenalty: Double,
    @SerialName("presence_penalty")
    val presencePenalty: Double,
)