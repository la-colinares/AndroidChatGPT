package com.lacolinares.androidchatgpt.presentation.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.androidchatgpt.R
import com.lacolinares.androidchatgpt.presentation.view.theme.Frostee
import com.lacolinares.androidchatgpt.presentation.view.theme.MineralGreen
import com.lacolinares.androidchatgpt.utils.AppConstants

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFE2F4E4)
@Composable
fun AppTopBar() {
    val openDialog = remember { mutableStateOf(false) }
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
                onClick = { openDialog.value = true }
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
    if (openDialog.value) {
        InfoDialog(onDismiss = { openDialog.value = false })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFFE2F4E4)
@Composable
private fun InfoDialog(
    onDismiss: () -> Unit = {}
) {
    val uriHandler = LocalUriHandler.current

    fun openUri(url: String){
        uriHandler.openUri(url)
    }

    AlertDialog(
        onDismissRequest = {
            onDismiss.invoke()
        }
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Frostee
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        tint = MineralGreen,
                        modifier = Modifier.clickable { onDismiss.invoke() }
                    )
                }
                InfoText(stringId = R.string.about_app)
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    InfoText(stringId = R.string.references)
                    RoundedButton(
                        stringId = R.string.open_ai_api,
                        onClick = { openUri(AppConstants.Urls.LINK_OPEN_AI) }
                    )
                    RoundedButton(
                        stringId = R.string.build_android_chat,
                        onClick = { openUri(AppConstants.Urls.LINK_BUILD_CHAT) }
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    InfoText(stringId = R.string.source_code)
                    RoundedButton(
                        stringId = R.string.github_source_code,
                        onClick = { openUri(AppConstants.Urls.LINK_SOURCE_CODE) }
                    )
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    InfoText(stringId = R.string.developer)
                    RoundedButton(
                        stringId = R.string.developer_name,
                        onClick = { openUri(AppConstants.Urls.LINK_DEVELOPER_WEBSITE) }
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoText(stringId: Int) {
    Text(
        text = stringResource(id = stringId),
        color = MineralGreen,
        fontWeight = FontWeight.SemiBold,
    )
}

@Composable
private fun RoundedButton(
    stringId: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = Modifier.height(28.dp),
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MineralGreen),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(
            text = stringResource(id = stringId),
            color = Frostee,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}