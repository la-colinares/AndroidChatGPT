package com.lacolinares.androidchatgpt.presentation.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.lacolinares.androidchatgpt.R
import com.lacolinares.androidchatgpt.R.raw
import com.lacolinares.androidchatgpt.presentation.view.theme.MineralGreen
import com.lacolinares.androidchatgpt.presentation.view.theme.SilverChalice

@Preview(showSystemUi = true, showBackground = true, backgroundColor = 0xFFE2F4E4)
@Composable
fun EmptyMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieEmptyMessage()
            Text(
                text = stringResource(R.string.empty_message_title),
                modifier = Modifier.fillMaxWidth(),
                color = MineralGreen,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.empty_message_description),
                modifier = Modifier.fillMaxWidth(),
                color = SilverChalice,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun LottieEmptyMessage() {
    val compositionResult: LottieCompositionResult = rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(raw.empty_message)
    )

    val progressAnimation by animateLottieCompositionAsState(
        compositionResult.value,
        isPlaying = true,
        iterations = LottieConstants.IterateForever,
        speed = 0.5f
    )

    LottieAnimation(
        composition = compositionResult.value,
        progress = progressAnimation,
        modifier = Modifier.size(200.dp)
    )
}