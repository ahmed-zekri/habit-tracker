package com.example.habittracker.ui.habitCreationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.habittracker.ui.screenUtils.RoundedIcon
import com.example.habittracker.ui.ScreenTopBar
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import com.example.habittracker.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitIconSelection(navHostController: NavHostController) =
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val searchItem = remember {
            mutableStateOf("")
        }
        ScreenTopBar(icon = Icons.Default.ExitToApp, text = "Select icon")
        Spacer(modifier = Modifier.height(15.dp))
        TextField(value = searchItem.value, onValueChange = { searchItem.value = it })

        LazyVerticalGrid(columns = GridCells.Adaptive(120.dp)) {
            items(FontAwesomeIcons.AllIcons.size) { index ->
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)) {
                    RoundedIcon(
                        circleColor = colorResource(id = R.color.primaryDark),
                        icon = FontAwesomeIcons.AllIcons[index], padding = 20.dp, iconSize = 40.dp
                    )
                }
            }
        }
    }