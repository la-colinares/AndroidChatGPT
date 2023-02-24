package com.lacolinares.androidchatgpt.data.remote

import com.lacolinares.androidchatgpt.data.model.MessageRequest
import com.lacolinares.androidchatgpt.data.model.MessageResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ChatServiceApi {
    @POST("v1/completions")
    suspend fun sendMessage(
        @Header("Authorization") authorization: String,
        @Header("Content-Type") contentType: String,
        @Body body: MessageRequest
    ): MessageResponse
}