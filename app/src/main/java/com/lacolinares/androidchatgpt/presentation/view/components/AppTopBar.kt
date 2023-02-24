package com.lacolinares.androidchatgpt.presentation.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.lacolinares.androidchatgpt.R
import com.lacolinares.androidchatgpt.presentation.view.theme.Frostee
import com.lacolinares.androidchatgpt.presentation.view.theme.MineralGreen

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFE2F4E4)
@Composable
fun AppTopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.toolbar_name),
                color = Frostee,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        actions = {
            IconButton(
                onClick = {  }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = Frostee
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MineralGreen)
    )
}