package com.catalin.activitiespractice

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.catalin.activitiespractice.ui.theme.ActivitiesPracticeTheme

class DataHandlingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivitiesPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowContent(intent)
                }
            }
        }
    }
}

@Composable
fun ShowContent(intent: Intent) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Data handling activity", fontWeight = FontWeight.Bold)
        if (intent.action == Intent.ACTION_SEND) {
            when (intent.type) {
                "text/plain" -> {
                    val textData = intent.extras?.get(Intent.EXTRA_TEXT)
                    textData?.let { ShowText(text = it.toString()) }
                }
                "image/*" -> {
                    val imgData = intent.extras?.get(Intent.EXTRA_STREAM)
                    ShowImage(img = imgData.toString())
                }
                else -> {
                    Text(text = "Unreadable data")
                }
            }
        } else {
            Text(text = "No data found")
        }
    }
}

@Composable
fun ShowText(text: String) {
    Text(text = text)
}

@Composable
fun ShowImage(img: String) {
    val painter = rememberAsyncImagePainter(model = Uri.parse(img))
    Image(painter = painter, contentDescription = null)
}




