package com.example.infoapp.ui_components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import com.example.infoapp.ui.theme.MainRed
import com.example.infoapp.R
import com.example.infoapp.ui.theme.BgTransparent
import com.example.infoapp.utils.DrawerEvents


@Composable
fun DrawerMenu(onEvent: (DrawerEvents) -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(colorResource(R.color.light_brown))){
        Column (modifier = Modifier.fillMaxSize()){
            Header()
            Body(){
                drawerEvents ->
                onEvent(drawerEvents)
            }
        }
    }
}


@Composable
fun Header() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(
            1.dp,
            colorResource(R.color.light_brown)
        )
    ){
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ){
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = "Background header",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Цікаві місця України",
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(colorResource(R.color.dark_cream))
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = White
            )
        }
    }

}


@Composable
fun Body(onEvent: (DrawerEvents) -> Unit) {

    val list = stringArrayResource(id = R.array.drawer_list)
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(list){
            index, title ->
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp, vertical = 5.dp),
                backgroundColor = colorResource(R.color.light_brown)
            ){
                Text(
                    text = title,
                    color = White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEvent(DrawerEvents.OnItemClick(title, index))
                        }
                        .padding(10.dp)
                        .wrapContentWidth().background(colorResource(R.color.light_brown)),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }


}