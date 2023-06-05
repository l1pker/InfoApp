package com.example.infoapp.ui_components

import AssetImage
import android.annotation.SuppressLint
import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.android.style.LetterSpacingSpanEm
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.infoapp.R
import com.example.infoapp.utils.IdArrayList
import com.example.infoapp.utils.ListItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun InfoScreen(item: ListItem, onClick: () -> Unit) {
    Scaffold(
        topBar = {

            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(text = item.title)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onClick()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.backspace),
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),

            shape = RoundedCornerShape(10.dp)

        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                item {

                    AssetImage(
                        imageName = item.imageName,
                        contentDescription = item.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            item.title,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            )
                    }
                    HTMLLoader(item.htmlName)
                }
            }
        }
    }
}

@Composable
fun HTMLLoader(htmlName: String) {
    val context = LocalContext.current
    val assetManger = context.assets
    val inputStream = assetManger.open("html/$htmlName")
    val size = inputStream.available()
    val buffer = ByteArray(size)
    inputStream.read(buffer)
    val htmlString = String(buffer)
    AndroidView(
        factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                loadData(
                    htmlString,
                    "text/html",
                    "utf-8"
                )
            }
        }
    )
}

private fun getDescription(index: Int, context: Context): List<ListItem> {
    val list = ArrayList<ListItem>()
    val arrayList = context.resources.getStringArray(IdArrayList.listId[index])
    arrayList.forEach { item ->
        val itemArray = item.split("|")
        list.add(
            ListItem(
                itemArray[0],
                itemArray[1],
                itemArray[2]
            )
        )
    }
    return list
}