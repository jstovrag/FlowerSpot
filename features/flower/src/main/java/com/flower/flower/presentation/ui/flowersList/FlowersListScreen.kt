package com.flower.flower.presentation.ui.flowersList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flower.base.components.FlowerSpotCircularProgressIndicator
import com.flower.base.utils.TAG_INPUT_FIELD
import com.flower.flower.R
import com.flower.theme.Black
import com.flower.theme.GrannySmith
import com.flower.theme.White
import com.flower.theme.WhiteSmoke

@Composable
fun FlowersListScreen(
    state: FlowersListState,
    events: (FlowersListEvents) -> Unit
) {
    val searchText = remember { mutableStateOf("") }
    val lastCreatedFlowerItem = remember { mutableStateOf(-1) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = WhiteSmoke)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = WhiteSmoke),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp),
                        contentScale = ContentScale.Crop,
                        painter = painterResource(R.drawable.image_flower_list_header),
                        contentDescription = "listHeader"
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 61.dp, start = 76.dp, end = 76.dp),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                color = White, fontSize = 24.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.ubuntu_light)
                                )
                            ),
                            text = stringResource(id = R.string.discover_flowers)
                        )

                        Text(
                            modifier = Modifier
                                .padding(top = 26.dp, start = 34.dp, end = 34.dp),
                            style = TextStyle(
                                color = White, fontSize = 15.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.ubuntu_light)
                                )
                            ),
                            text = stringResource(id = R.string.explore_flowers_info),
                            textAlign = TextAlign.Center,
                        )

                        TextField(
                            modifier = Modifier
                                .testTag(TAG_INPUT_FIELD)
                                .fillMaxWidth()
                                .padding(top = 29.dp, start = 24.dp, end = 24.dp),
                            textStyle = TextStyle(
                                color = Black, fontSize = 14.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.ubuntu_regular)
                                )
                            ),
                            shape = RoundedCornerShape(3.dp),
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.None,
                                autoCorrect = true,
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    events(FlowersListEvents.SearchFlowers(searchText.value))
                                }
                            ),
                            singleLine = true,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = White,
                                cursorColor = Black,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                placeholderColor = GrannySmith,
                                focusedLabelColor = Black
                            ),
                            value = searchText.value,
                            onValueChange = {
                                searchText.value = it
                            },
                            label = { Text(stringResource(id = R.string.looking_for_something_specific)) },
                            trailingIcon = {
                                Image(
                                    modifier = Modifier.clickable(
                                        enabled = true,
                                        onClick = {
                                            events(FlowersListEvents.SearchFlowers(searchText.value))
                                        }
                                    ),
                                    contentScale = ContentScale.Crop,
                                    painter = painterResource(R.drawable.ic_search),
                                    contentDescription = "iconSearch"
                                )
                            }
                        )
                    }
                }

                FlowerSpotCircularProgressIndicator(
                    modifier = Modifier.padding(top = 40.dp),
                    isLoading = state.isLoading
                )
            }

            if (state.flowers?.flowers != null)
                items(state.flowers.flowers.size) { index ->
                    lastCreatedFlowerItem.value = -1
                    if (lastCreatedFlowerItem.value != index) {
                        Row {
                            FlowersListItem(
                                Modifier
                                    .weight(1f)
                                    .padding(start = 20.dp),
                                flower = state.flowers.flowers[index]
                            )
                            lastCreatedFlowerItem.value = index
                            Spacer(modifier = Modifier.width(12.dp))
                            if (index + 1 < state.flowers.flowers.size)
                                FlowersListItem(
                                    Modifier
                                        .weight(1f)
                                        .padding(end = 20.dp),
                                    flower = state.flowers.flowers[index + 1]
                                )
                            lastCreatedFlowerItem.value = index + 1
                        }
                    }
                }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        ErrorDialog(state = state, events = events)
    }
}

@Composable
private fun ErrorDialog(
    state: FlowersListState,
    events: (FlowersListEvents) -> Unit
) {
    if (state.dialog != null && state.dialogVisibility) {
        AlertDialog(
            onDismissRequest = {
            },
            text = {
                Text(stringResource(id = R.string.something_went_wrong))
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = GrannySmith),
                    onClick = {
                        events(FlowersListEvents.DialogVisibility(false))
                    },
                    content = { Text(stringResource(id = R.string.ok)) }
                )
            }

        )
    }
}
