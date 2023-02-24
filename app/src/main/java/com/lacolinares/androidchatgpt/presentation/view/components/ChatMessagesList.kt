package com.lacolinares.androidchatgpt.presentation.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.androidchatgpt.domain.model.ChatMessage
import com.lacolinares.androidchatgpt.presentation.view.theme.MineralGreen
import com.lacolinares.androidchatgpt.presentation.view.theme.White
import com.lacolinares.androidchatgpt.utils.MessageType
import kotlinx.coroutines.launch

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChatMessagesList(
    chatMessages: List<ChatMessage> = emptyList()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        val coroutineScope = rememberCoroutineScope()
        val listState = rememberLazyListState()
        val keyboardIsOpen = WindowInsets.isImeVisible

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = listState,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(chatMessages, key = { it.dateTime }) { item ->
                when (item.messageType) {
                    MessageType.USER -> UserChat(item.message)
                    MessageType.AI -> AiChat(item.message)
                }
            }
        }

        LaunchedEffect(key1 = keyboardIsOpen, key2 = chatMessages.size){
            coroutineScope.launch {
                if (keyboardIsOpen || chatMessages.isNotEmpty()){
                    listState.animateScrollToItem(chatMessages.lastIndex)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE2F4E4)
@Composable
private fun UserChat(message: String = "") {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd
    ) {
        Card(
            modifier = Modifier.wrapContentWidth().padding(start = 32.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MineralGreen)
        ) {
            MessageText(text = message, color = White)
        }
    }
}

@Composable
private fun AiChat(message: String = "") {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier.wrapContentWidth().padding(end = 32.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = White)
        ) {
            MessageText(text = message, color = MineralGreen)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE2F4E4)
@Composable
private fun MessageText(text: String = "", color: Color = MineralGreen) {
    Text(
        text = text,
        modifier = Modifier
            .wrapContentWidth()
            .padding(12.dp),
        color = color,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        textAlign = TextAlign.Start,
        lineHeight = 16.sp
    )
}