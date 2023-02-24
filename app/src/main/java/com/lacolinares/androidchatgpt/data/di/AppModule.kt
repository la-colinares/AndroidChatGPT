package com.lacolinares.androidchatgpt.data.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lacolinares.androidchatgpt.data.remote.ChatServiceApi
import com.lacolinares.androidchatgpt.data.repository.ChatRepositoryImpl
import com.lacolinares.androidchatgpt.domain.repository.ChatRepository
import com.lacolinares.androidchatgpt.utils.AppConstants
import com.lacolinares.androidchatgpt.utils.AppJson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton


@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideChatServiceApi(): ChatServiceApi {
        val contentType = AppConstants.APPLICATION_TYPE.toMediaType()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(client)
            .addConverterFactory(AppJson.Json.asConverterFactory(contentType))
            .build()
            .create(ChatServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideChatRepository(
        @ApplicationContext context: Context,
        api: ChatServiceApi
    ): ChatRepository = ChatRepositoryImpl(context, api)
}