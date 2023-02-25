package com.lacolinares.androidchatgpt.presentation.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
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
            items(chatMessages, key = { it.id }) {
                CardMessage(it)
            }
        }

        LaunchedEffect(key1 = keyboardIsOpen, key2 = chatMessages.size) {
            coroutineScope.launch {
                if (keyboardIsOpen || chatMessages.isNotEmpty()) {
                    listState.animateScrollToItem(chatMessages.lastIndex)
                }
            }
        }
    }
}

@Composable
private fun CardMessage(chatMessage: ChatMessage) {
    val isUser = chatMessage.messageType == MessageType.USER
    val boxAlignment = if (isUser) Alignment.CenterEnd else Alignment.CenterStart
    val cardPadding = if (isUser) Modifier.padding(start = 32.dp) else Modifier.padding(end = 32.dp)
    val cardColor = if (isUser) MineralGreen else White
    val textColor = if (isUser) White else MineralGreen

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = boxAlignment
    ) {
        Card(
            modifier = Modifier
                .wrapContentWidth()
                .then(cardPadding),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            MessageText(text = chatMessage.message, color = textColor)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFE2F4E4)
@Composable
private fun MessageText(text: String = "", color: Color = MineralGreen) {
    SelectionContainer {
        Text(
            text = text.trimMargin(),
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
}