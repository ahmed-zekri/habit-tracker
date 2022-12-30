package com.example.habittracker.ui.habitCreationScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.habittracker.R
import com.example.habittracker.data.utils.navigateToRoute
import com.example.habittracker.ui.navigation.Destinations
import com.example.habittracker.ui.screenUtils.RoundedIcon
import com.example.habittracker.ui.screenUtils.ScreenTopBar
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.TablerIcons
import compose.icons.tablericons.ChevronLeft
import compose.icons.tablericons.Search
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HabitIconSelection(
    habitCreationViewModel: HabitCreationViewModel, navHostController: NavHostController

) =
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.primary)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val searchItem = remember {
            mutableStateOf("")
        }
        val allIcons = buildList {
            addAll(FontAwesomeIcons.AllIcons)
            addAll(TablerIcons.AllIcons)
        }
        val iconsListState = remember {
            mutableStateOf(allIcons)
        }
        val selectedIcon = remember {
            mutableStateOf<ImageVector?>(null)
        }
        ScreenTopBar(icon = TablerIcons.ChevronLeft, text = "Select icon") {
            navHostController.apply {
                habitCreationViewModel.updateOrGetHabit(icon = selectedIcon.value?.name)
                navigateToRoute(
                    route = Destinations.HabitCreationConfirmation.path
                )
            }
        }
        LaunchedEffect(key1 = searchItem.value) {
            if (searchItem.value.isEmpty())
                iconsListState.value = allIcons
            else
                iconsListState.value =
                    withContext(Dispatchers.IO) {
                        allIcons.filter {
                            searchItem.value.lowercase().trim() in it.name.lowercase()
                        }
                    }

        }

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            leadingIcon = {
                Icon(
                    imageVector = TablerIcons.Search,
                    contentDescription = null,
                    tint = Color.White
                )
            },
            value = searchItem.value,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(
                    id = R.color.transparentTextFieldTone
                ),
                textColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(16.dp),
            onValueChange = { searchItem.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )

        LazyVerticalGrid(columns = GridCells.Adaptive(120.dp)) {
            items(iconsListState.value.size) { index ->
                iconsListState.value[index].apply {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp)
                    ) {
                        RoundedIcon(
                            modifier = Modifier.clickable {
                                if (selectedIcon.value == this@apply)
                                    selectedIcon.value = null
                                else
                                    selectedIcon.value = this@apply
                            },
                            circleColor = if (this@apply == selectedIcon.value) Color.White else colorResource(
                                id = R.color.primaryDark
                            ),
                            icon = this@apply,
                            padding = 20.dp,
                            iconSize = 40.dp,
                            iconTint = if (this@apply == selectedIcon.value) Color.Black else Color.White
                        )
                    }
                }

            }
        }
    }