package com.lacolinares.androidchatgpt.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val id: String? = null,
    @SerialName("object")
    val obj: String? = null,
    val created: Int? = null,
    val model: String? = null,
    val choices: List<Choices> = listOf(),
    val usage: Usage? = Usage()
) {
    @Serializable
    data class Choices(
        val text: String? = null,
        val index: Int? = null,
        @SerialName("logprobs")
        val logProbs: String? = null,
        @SerialName("finish_reason")
        val finishReason: String? = null
    )

    @Serializable
    data class Usage(
        @SerialName("prompt_tokens")
        val promptTokens: Int? = null,
        @SerialName("completion_tokens")
        val completionTokens: Int? = null,
        @SerialName("total_tokens")
        val totalTokens: Int? = null
    )
}