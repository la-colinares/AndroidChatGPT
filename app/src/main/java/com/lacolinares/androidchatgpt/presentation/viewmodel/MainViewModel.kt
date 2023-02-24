package com.lacolinares.androidchatgpt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lacolinares.androidchatgpt.domain.model.ChatMessage
import com.lacolinares.androidchatgpt.domain.usecases.GetMessageUseCase
import com.lacolinares.androidchatgpt.domain.usecases.SaveMessageUseCase
import com.lacolinares.androidchatgpt.domain.usecases.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMessageUseCase: GetMessageUseCase,
    private val saveMessageUseCase: SaveMessageUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    init {
        loadMessages()
    }

    fun sendMessage(message: String) {
        safeLaunch {
            val chatMessage = ChatMessage(message = message)
            saveMessageUseCase(
                chatMessage = chatMessage,
                onSuccess = {
                    safeLaunch {
                        sendMessageUseCase(message)
                    }
                }
            )
        }
    }

    private fun loadMessages() {
        safeLaunch {
            getMessageUseCase().collect { messages -> _messages.update { messages } }
        }
    }

    private fun safeLaunch(func: suspend () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            func.invoke()
        }
    }
}