package com.lacolinares.androidchatgpt.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.lacolinares.androidchatgpt.presentation.view.components.AppBottomBar
import com.lacolinares.androidchatgpt.presentation.view.components.AppTopBar
import com.lacolinares.androidchatgpt.presentation.view.components.ChatMessagesList
import com.lacolinares.androidchatgpt.presentation.view.components.EmptyMessage
import com.lacolinares.androidchatgpt.presentation.view.theme.AndroidChatGPTTheme
import com.lacolinares.androidchatgpt.presentation.view.theme.Frostee
import com.lacolinares.androidchatgpt.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            AndroidChatGPTTheme {
                ChatGPT(viewModel)
                BackHandler(onBack = this::finish)
            }
        }
    }
}

@Composable
fun ChatGPT(viewModel: MainViewModel) {
    val messages = viewModel.messages.collectAsState().value

    Scaffold(
        topBar = { AppTopBar() },
        bottomBar = {
            AppBottomBar(onSend = { message -> viewModel.sendMessage(message) })
        },
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Frostee)
                .padding(it)
        ) {
            if (messages.isNotEmpty()) {
                ChatMessagesList(messages)
            } else {
                EmptyMessage()
            }
        }
    }
}
