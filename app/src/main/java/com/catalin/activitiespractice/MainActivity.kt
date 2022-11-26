package com.catalin.activitiespractice

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.catalin.activitiespractice.ui.theme.ActivitiesPracticeTheme

data class Product(
    var id: Int,
    var name: String?,
    var price: Double,
    var quantity: Int,
    var rating: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeDouble(price)
        parcel.writeInt(quantity)
        parcel.writeDouble(rating)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }


        private val products = listOf(
            Product(0, "Desk", 99.99, 100, 4.5),
            Product(1, "Couch", 499.99, 300, 4.3),
            Product(2, "Bed", 359.99, 200, 4.1),
            Product(3, "Table", 159.99, 80, 3.9),
            Product(4, "Chair", 29.99, 400, 4.3),
            Product(5, "TV", 599.99, 70, 4.6),
            Product(6, "Washer", 299.99, 100, 4.2),
            Product(7, "Chest of drawers", 199.99, 100, 4.1),
            Product(8, "Lamp", 19.99, 250, 4.3),
            Product(9, "Oven", 199.99, 130, 4.0),
        )

        fun getProducts() = products

        fun getProduct(id: Int): Product? {
            for (i in 0..9) {
                if (products[i].id == id) {
                    return products[i]
                    break
                }
            }
            return null
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivitiesPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    MainActivityContent()
                    ProductsList()
                }
            }
        }
    }
}

@Composable
fun ProductsList() {
    val ctx = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            val photoActIntent = Intent(ctx, PhotoActivity::class.java)
            ctx.startActivity(photoActIntent)
        }) {
            Text(text = "to PhotoActivity")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(Product.getProducts()) {
                Column(modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .background(Color(0xffeeeeee))
                    .clickable {
                        val productDetailIntent = Intent(ctx, ProductDetailActivity::class.java)
//                    productDetailIntent.putExtra("prodId", it.id)
                        productDetailIntent.putExtra("prod", it)
                        ctx.startActivity(productDetailIntent)
                    }
                    .padding(8.dp)
                ) {
                    Text(text = it.name ?: "", fontWeight = FontWeight.Bold)
                    Text(text = it.price.toString())
                }
            }
        }
    }

}

//@Composable
//fun MainActivityContent() {
//    val ctx = LocalContext.current
//
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Button(onClick = {
//            val toSecond = Intent(ctx, SecondActivity::class.java)
//            ctx.startActivity(toSecond)
//        }) {
//            Text(text = "Move to second activity")
//        }
//    }
//}



