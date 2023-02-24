package com.lacolinares.androidchatgpt.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.androidchatgpt.presentation.view.theme.Frostee
import com.lacolinares.androidchatgpt.presentation.view.theme.MineralGreen
import com.lacolinares.androidchatgpt.presentation.view.theme.SilverChalice

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Preview(showBackground = true, backgroundColor = 0xFFE2F4E4)
@Composable
fun AppBottomBar(
    onSend: (String) -> Unit = {},
) {
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(MineralGreen)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        var text by remember { mutableStateOf("") }

        if (WindowInsets.isImeVisible.not()){
            focusManager.clearFocus()
        }

        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 16.dp, vertical = 0.dp)
                .weight(9f)
                .focusRequester(focusRequester),
            placeholder = {
                Text(
                    text = "Type a message...",
                    color = MineralGreen,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable { text = "" },
                        tint = MineralGreen
                    )
                }
            },
            shape = RoundedCornerShape(50.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Frostee,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor  = Color.Transparent,
                disabledIndicatorColor  = Color.Transparent,
            )
        )
        if (text.isNotEmpty() && text.isNotBlank()){
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .weight(1f, false)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(bounded = false, color = SilverChalice),
                        onClick = {
                            onSend.invoke(text)
                            text = ""
                            focusManager.clearFocus()
                        }
                    ),
                tint = Frostee
            )
        }
    }
}