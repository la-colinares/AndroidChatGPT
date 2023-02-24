package com.lacolinares.androidchatgpt.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.lacolinares.androidchatgpt.presentation.view.theme.AndroidChatGPTTheme
import com.lacolinares.androidchatgpt.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            AndroidChatGPTTheme {
                ChatGPT(viewModel)
            }
        }
    }
}

@Composable
fun ChatGPT(viewModel: MainViewModel) {

}
