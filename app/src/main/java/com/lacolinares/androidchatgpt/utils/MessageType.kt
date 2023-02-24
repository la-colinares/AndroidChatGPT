package com.lacolinares.androidchatgpt.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable(with = MessageTypeSerializer::class)
enum class MessageType(val value: String) {
    USER("USER"),
    AI("AI")
}