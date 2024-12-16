package com.application.myapplication

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisplayAndroidIdScreen()
        }
    }
}

@Composable
fun DisplayAndroidIdScreen() {
    val context = LocalContext.current
    val androidId = remember {
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "ANDROID_ID:",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = androidId,
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                copyToClipboard(context, androidId)
                Toast.makeText(context, "ANDROID_ID скопирован в буфер обмена", Toast.LENGTH_SHORT).show()
            },
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            copyToClipboard(context, androidId)
            Toast.makeText(context, "ANDROID_ID скопирован в буфер обмена", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Скопировать ANDROID_ID")
        }
    }
}

fun copyToClipboard(context: android.content.Context, text: String) {
    val clipboard = context.getSystemService(ClipboardManager::class.java)
    val clip = ClipData.newPlainText("ANDROID_ID", text)
    clipboard.setPrimaryClip(clip)
}
