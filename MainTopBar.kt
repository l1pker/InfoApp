package com.example.infoapp.ui_components
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch


@Composable
fun MainTopBar(title: String, scaffoldState: ScaffoldState) {
    val coroutine = rememberCoroutineScope()
    TopAppBar(
        backgroundColor = Color.White,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    coroutine.launch{
                        scaffoldState.drawerState.open()
                    }
                }
            ){
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        }
    )

}