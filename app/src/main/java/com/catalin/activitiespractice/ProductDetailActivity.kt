package com.catalin.activitiespractice

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.catalin.activitiespractice.ui.theme.ActivitiesPracticeTheme

class ProductDetailActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivitiesPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    val id = intent.getIntExtra("prodId", -1)
//                    if (id > -1) {
//                        ProductDetail(prodId = id)
//                    } else {
//                        Text(text = "No product available")
//                    }
                    val prod = intent.getParcelableExtra<Product>("prod")
                    if (prod != null) {
                        ProductDetail(prod)
                    } else {
                        Text(text = "No product available")
                    }
                }
            }
        }
    }
}

@Composable
//fun ProductDetail(prodId: Int) {
fun ProductDetail(prod: Product) {
    val ctx = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Product detail activity", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//        Text(text = prodId.toString())
        Text(text = prod.toString())
        Icon(Icons.Default.Share, contentDescription = null,
            modifier = Modifier.clickable {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.putExtra(Intent.EXTRA_TEXT, prod.toString())
                shareIntent.type = "text/plain"
                try {
                    ctx.startActivity(shareIntent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
        )
    }
}








